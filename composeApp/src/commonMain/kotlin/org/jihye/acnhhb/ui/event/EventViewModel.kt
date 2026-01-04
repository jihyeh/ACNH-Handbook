package org.jihye.acnhhb.ui.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import org.jihye.acnhhb.domain.model.Event
import org.jihye.acnhhb.domain.repository.EventRepository
import org.jihye.acnhhb.domain.repository.SettingsRepository
import kotlin.time.Clock

class EventViewModel(
    eventRepository: EventRepository,
    settingsRepository: SettingsRepository,
) : ViewModel() {

    private val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    private val currentYear = today.year

    val state: StateFlow<EventListState> =
        combine(
            eventRepository.getEvents(year = currentYear.toString()),
            eventRepository.getEvents(year = (currentYear + 1).toString()),
            settingsRepository.isNorth,
        ) { thisYearEvents, nextYearEvents, isNorth ->
            /**
             * - 과거 데이터는 비노출
             * - 현재 월의 이벤트가 최상단
             * - 반구 필터링
             * - 내년 이벤트까지 포함
             * */
            val allEvents = (thisYearEvents + nextYearEvents).sortedBy { it.date }
            val currentMonthStart =
                "${today.year}-${today.month.number.toString().padStart(2, '0')}-01"

            val filteredEvents =
                allEvents.filter { event ->
                    val isHemisphereMatch =
                        when (isNorth) {
                            true -> {
                                (event.hemisphere == Event.HemisphereType.NORTHERN) ||
                                    (event.hemisphere == Event.HemisphereType.BOTH)
                            }

                            false -> {
                                (event.hemisphere == Event.HemisphereType.SOUTHERN) ||
                                    (event.hemisphere == Event.HemisphereType.BOTH)
                            }
                        }

                    val isFutureOrPresent = event.date >= currentMonthStart
                    isHemisphereMatch && isFutureOrPresent
                }.map { event ->
                    EventUiModel(
                        event = event,
                        isToday = event.date == today.toString()
                    )
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

data class EventUiModel(
    val event: Event,
    val isToday: Boolean,
)

sealed interface EventListState {
    data object Loading : EventListState
    data class Success(val events: List<EventUiModel>) : EventListState
    data class Error(val message: String) : EventListState
}
