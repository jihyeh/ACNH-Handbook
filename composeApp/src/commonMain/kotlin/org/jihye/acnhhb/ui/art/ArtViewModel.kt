package org.jihye.acnhhb.ui.art

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Art
import org.jihye.acnhhb.domain.repository.ArtRepository

class ArtViewModel(
    artRepository: ArtRepository,
) : ViewModel() {
    val state: StateFlow<ArtListState> =
        artRepository
            .getArts()
            .map { ArtListState.Success(it) as ArtListState }
            .onStart { emit(ArtListState.Loading) }
            .catch { emit(ArtListState.Error(it.message ?: "Unknown error")) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ArtListState.Loading,
            )
}

sealed interface ArtListState {
    data object Loading : ArtListState
    data class Success(val arts: List<Art>) : ArtListState
    data class Error(val message: String) : ArtListState
}
