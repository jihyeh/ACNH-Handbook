package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Photo
import org.jihye.acnhhb.domain.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : PhotoRepository {
    override fun getPhotos(
        isExcludeDetails: String?,
    ): Flow<List<Photo>> = flow {
        val remotePhotos = remoteDataSource.fetchPhotos(isExcludeDetails = isExcludeDetails)
        emit(remotePhotos.map { it.toDomain() })
    }
}
