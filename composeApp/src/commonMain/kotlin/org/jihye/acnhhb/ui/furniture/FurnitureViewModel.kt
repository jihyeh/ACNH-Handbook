package org.jihye.acnhhb.ui.furniture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Furniture
import org.jihye.acnhhb.domain.repository.FurnitureRepository

class FurnitureViewModel(
    furnitureRepository: FurnitureRepository,
) : ViewModel() {

    val state: StateFlow<FurnitureListState> =
        furnitureRepository
            .getFurniture(
                category = null,
                color = null,
            )
            .map { furniture ->
                FurnitureListState.Success(furniture) as FurnitureListState
            }
            .catch { exception ->
                emit(FurnitureListState.Error(exception.message ?: "Unknown error"))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = FurnitureListState.Loading,
            )
}

sealed interface FurnitureListState {
    data object Loading : FurnitureListState
    data class Success(val furniture: List<Furniture>) : FurnitureListState
    data class Error(val message: String) : FurnitureListState
}
