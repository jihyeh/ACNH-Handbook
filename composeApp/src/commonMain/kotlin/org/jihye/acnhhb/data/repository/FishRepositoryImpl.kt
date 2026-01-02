package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Fish
import org.jihye.acnhhb.domain.repository.FishRepository

class FishRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val fishNameProvider: FishNameProvider,
) : FishRepository {

    override fun getFishes(): Flow<List<Fish>> = flow {
        coroutineScope {
            val remoteFishDeferred = async {
                remoteDataSource.fetchFishes(
                    month = null,
                    isExcludeDetails = null,
                    thumbnailSize = null
                )
            }
            async { fishNameProvider.load() }.await()

            val remoteFish = remoteFishDeferred.await()

            emit(
                remoteFish.map {
                    val localizedName = fishNameProvider.getName(it.name)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
