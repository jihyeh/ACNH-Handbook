package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Photo

interface PhotoRepository {
    fun getPhotos(
        isExcludeDetails: String? = null,
    ): Flow<List<Photo>>
}
