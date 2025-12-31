package org.jihye.acnhhb.ui.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Photo
import org.jihye.acnhhb.domain.repository.PhotoRepository

class PhotoViewModel(
    photoRepository: PhotoRepository,
) : ViewModel() {

    val state: StateFlow<PhotoListState> =
        photoRepository
            .getPhotos(isExcludeDetails = null)
            .map { photos -> PhotoListState.Success(photos) as PhotoListState }
            .catch { exception ->
                emit(PhotoListState.Error(exception.message ?: "Unknown error"))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PhotoListState.Loading,
            )
}

sealed interface PhotoListState {
    data object Loading : PhotoListState
    data class Success(val photos: List<Photo>) : PhotoListState
    data class Error(val message: String) : PhotoListState
}
