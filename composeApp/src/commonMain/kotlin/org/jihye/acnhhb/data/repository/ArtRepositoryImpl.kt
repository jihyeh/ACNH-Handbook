package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Art
import org.jihye.acnhhb.domain.repository.ArtRepository

class ArtRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : ArtRepository {

    override fun getArts(): Flow<List<Art>> = flow {
        val remoteArts = remoteDataSource.fetchArt(
            isExcludeFakes = null,
            thumbnailSize = null
        )
        emit(remoteArts.map { it.toDomain() })
    }
}
