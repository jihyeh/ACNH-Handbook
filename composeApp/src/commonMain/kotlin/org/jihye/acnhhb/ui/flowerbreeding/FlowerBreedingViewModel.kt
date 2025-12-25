package org.jihye.acnhhb.ui.flowerbreeding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.FlowerBreedingData
import org.jihye.acnhhb.domain.repository.FlowerBreedingRepository

class FlowerBreedingViewModel(
    private val repository: FlowerBreedingRepository,
) : ViewModel() {

    val state: StateFlow<FlowerBreedingState> = flow {
        emit(FlowerBreedingState.Loading)
        try {
            val data = repository.getFlowerBreedingData()
            emit(FlowerBreedingState.Success(data))
        } catch (e: Exception) {
            emit(FlowerBreedingState.Error(e.message ?: "Unknown error"))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FlowerBreedingState.Loading
    )
}

sealed interface FlowerBreedingState {
    data object Loading : FlowerBreedingState
    data class Success(val data: FlowerBreedingData) : FlowerBreedingState
    data class Error(val message: String) : FlowerBreedingState
}
