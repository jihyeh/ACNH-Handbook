package org.jihye.acnhhb.ui.fossil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Fossil
import org.jihye.acnhhb.domain.repository.FossilRepository

class FossilViewModel(
    fossilRepository: FossilRepository,
) : ViewModel() {
    val state: StateFlow<FossilListState> =
        fossilRepository
            .getFossils()
            .map { FossilListState.Success(it) as FossilListState }
            .onStart { emit(FossilListState.Loading) }
            .catch { emit(FossilListState.Error(it.message ?: "Unknown error")) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = FossilListState.Loading,
            )
}

sealed interface FossilListState {
    data object Loading : FossilListState
    data class Success(val fossils: List<Fossil>) : FossilListState
    data class Error(val message: String) : FossilListState
}
