package org.jihye.acnhhb.ui.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jihye.acnhhb.domain.model.Event
import org.jihye.acnhhb.domain.repository.EventRepository
import org.jihye.acnhhb.domain.repository.SettingsRepository
import kotlin.time.Clock

class EventViewModel(
    eventRepository: EventRepository,
    settingsRepository: SettingsRepository,
) : ViewModel() {

    private val currentYear = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year

    val state: StateFlow<EventListState> =
        combine(
            eventRepository.getEvents(year = currentYear.toString()),
            settingsRepository.isNorth,
        ) { events, isNorth ->
            val filteredEvents = events.filter { event ->
                when (isNorth) {
                    true -> {
                        (event.hemisphere == Event.HemisphereType.NORTHERN) || (event.hemisphere == Event.HemisphereType.BOTH)
                    }

                    false -> {
                        (event.hemisphere == Event.HemisphereType.SOUTHERN) || (event.hemisphere == Event.HemisphereType.BOTH)
                    }
                }
            }
            EventListState.Success(filteredEvents) as EventListState
        }
            .catch { exception ->
                emit(EventListState.Error(exception.message ?: "Unknown error"))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = EventListState.Loading,
            )
}

sealed interface EventListState {
    data object Loading : EventListState
    data class Success(val events: List<Event>) : EventListState
    data class Error(val message: String) : EventListState
}
