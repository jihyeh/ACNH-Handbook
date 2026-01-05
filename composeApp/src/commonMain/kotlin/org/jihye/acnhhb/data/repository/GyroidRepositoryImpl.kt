package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.provider.GyroidNameProvider
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Gyroid
import org.jihye.acnhhb.domain.repository.GyroidRepository

class GyroidRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val gyroidNameProvider: GyroidNameProvider,
) : GyroidRepository {

    override fun getGyroids(): Flow<List<Gyroid>> = flow {
        coroutineScope {
            val remoteGyroidsDeferred = async {
                remoteDataSource.fetchGyroids(
                    sound = null,
                    isExcludeDetails = null,
                )
            }
            async { gyroidNameProvider.load() }.await()

            val remoteGyroids = remoteGyroidsDeferred.await()

            emit(
                remoteGyroids.map {
                    val localizedName = gyroidNameProvider.getName(it.name)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
