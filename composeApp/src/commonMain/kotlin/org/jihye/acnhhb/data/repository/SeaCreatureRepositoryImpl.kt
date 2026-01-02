package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.SeaCreature
import org.jihye.acnhhb.domain.repository.SeaCreatureRepository

class SeaCreatureRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val seaCreatureNameProvider: SeaCreatureNameProvider,
) : SeaCreatureRepository {

    override fun getSeaCreatures(): Flow<List<SeaCreature>> = flow {
        coroutineScope {
            val remoteSeaCreaturesDeferred = async {
                remoteDataSource.fetchSeaCreatures(
                    month = null,
                    isExcludeDetails = null,
                    thumbnailSize = null
                )
            }
            async { seaCreatureNameProvider.load() }.await()

            val remoteSeaCreatures = remoteSeaCreaturesDeferred.await()

            emit(
                remoteSeaCreatures.map {
                    val localizedName = seaCreatureNameProvider.getName(it.name)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
