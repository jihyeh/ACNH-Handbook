package org.jihye.acnhhb.ui.sea

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.SeaCreature
import org.jihye.acnhhb.domain.repository.SeaCreatureRepository

class SeaCreatureViewModel(
    seaCreatureRepository: SeaCreatureRepository,
) : ViewModel() {
    val state: StateFlow<SeaCreatureListState> =
        seaCreatureRepository
            .getSeaCreatures()
            .map { SeaCreatureListState.Success(it) as SeaCreatureListState }
            .onStart { emit(SeaCreatureListState.Loading) }
            .catch { emit(SeaCreatureListState.Error(it.message ?: "Unknown error")) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SeaCreatureListState.Loading,
            )
}

sealed interface SeaCreatureListState {
    data object Loading : SeaCreatureListState
    data class Success(val seaCreatures: List<SeaCreature>) : SeaCreatureListState
    data class Error(val message: String) : SeaCreatureListState
}
