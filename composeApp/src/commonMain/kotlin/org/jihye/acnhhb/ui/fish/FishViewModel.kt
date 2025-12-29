package org.jihye.acnhhb.ui.fish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Fish
import org.jihye.acnhhb.domain.repository.FishRepository

class FishViewModel(
    fishRepository: FishRepository,
) : ViewModel() {
    val state: StateFlow<FishListState> =
        fishRepository
            .getFishes()
            .map { FishListState.Success(it) as FishListState }
            .onStart { emit(FishListState.Loading) }
            .catch { emit(FishListState.Error(it.message ?: "Unknown error")) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = FishListState.Loading,
            )
}

sealed interface FishListState {
    data object Loading : FishListState
    data class Success(val fishes: List<Fish>) : FishListState
    data class Error(val message: String) : FishListState
}
