package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.SeaCreature
import org.jihye.acnhhb.domain.repository.SeaCreatureRepository

class SeaCreatureRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : SeaCreatureRepository {

    override fun getSeaCreatures(): Flow<List<SeaCreature>> = flow {
        val remoteSeaCreatures =
            remoteDataSource.fetchSeaCreatures(
                month = null,
                isExcludeDetails = null,
                thumbnailSize = null
            )
        emit(remoteSeaCreatures.map { it.toDomain() })
    }
}
