package org.jihye.acnhhb.ui.interior

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Interior
import org.jihye.acnhhb.domain.repository.InteriorRepository

class InteriorViewModel(
    interiorRepository: InteriorRepository,
) : ViewModel() {

    val state: StateFlow<InteriorListState> =
        interiorRepository
            .getInterior(
                category = null,
                color = null,
            )
            .map { interior -> InteriorListState.Success(interior) as InteriorListState }
            .catch { exception ->
                emit(InteriorListState.Error(exception.message ?: "Unknown error"))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = InteriorListState.Loading,
            )
}

sealed interface InteriorListState {
    data object Loading : InteriorListState
    data class Success(val interior: List<Interior>) : InteriorListState
    data class Error(val message: String) : InteriorListState
}
