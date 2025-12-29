package org.jihye.acnhhb.ui.gyroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Gyroid
import org.jihye.acnhhb.domain.repository.GyroidRepository

class GyroidViewModel(
    gyroidRepository: GyroidRepository,
) : ViewModel() {
    val state: StateFlow<GyroidListState> =
        gyroidRepository
            .getGyroids()
            .map { GyroidListState.Success(it) as GyroidListState }
            .onStart { emit(GyroidListState.Loading) }
            .catch { emit(GyroidListState.Error(it.message ?: "Unknown error")) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = GyroidListState.Loading,
            )
}

sealed interface GyroidListState {
    data object Loading : GyroidListState
    data class Success(val gyroids: List<Gyroid>) : GyroidListState
    data class Error(val message: String) : GyroidListState
}
