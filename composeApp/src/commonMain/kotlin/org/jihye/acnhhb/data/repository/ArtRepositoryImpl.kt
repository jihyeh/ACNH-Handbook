package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Art
import org.jihye.acnhhb.domain.repository.ArtRepository

class ArtRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val artNameProvider: ArtNameProvider,
) : ArtRepository {

    override fun getArts(): Flow<List<Art>> = flow {
        coroutineScope {
            val remoteArtsDeferred = async {
                remoteDataSource.fetchArt(isExcludeFakes = null, thumbnailSize = null)
            }
            async { artNameProvider.load() }.await()

            val remoteArts = remoteArtsDeferred.await()

            emit(
                remoteArts.map {
                    val localizedName = artNameProvider.getName(it.name)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
