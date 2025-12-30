package org.jihye.acnhhb.ui.clothing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Clothing
import org.jihye.acnhhb.domain.repository.ClothingRepository

class ClothingViewModel(
    clothingRepository: ClothingRepository,
) : ViewModel() {

    val state: StateFlow<ClothingListState> =
        clothingRepository
            .getClothing(
                category = null,
                color = null,
                style = null,
                labelTheme = null
            )
            .map { clothing ->
                ClothingListState.Success(clothing) as ClothingListState
            }
            .catch { exception ->
                emit(ClothingListState.Error(exception.message ?: "Unknown error"))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ClothingListState.Loading,
            )
}

sealed interface ClothingListState {
    data object Loading : ClothingListState
    data class Success(val clothing: List<Clothing>) : ClothingListState
    data class Error(val message: String) : ClothingListState
}
