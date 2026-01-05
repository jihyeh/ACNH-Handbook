package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.provider.PhotoNameProvider
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Photo
import org.jihye.acnhhb.domain.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val photoNameProvider: PhotoNameProvider,
) : PhotoRepository {
    override fun getPhotos(
        isExcludeDetails: String?,
    ): Flow<List<Photo>> = flow {
        coroutineScope {
            val remotePhotosDeferred = async {
                remoteDataSource.fetchPhotos(isExcludeDetails = isExcludeDetails)
            }
            async { photoNameProvider.load() }.await()

            val remotePhotos = remotePhotosDeferred.await()

            emit(
                remotePhotos.map {
                    val localizedName = photoNameProvider.getName(it.name)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
