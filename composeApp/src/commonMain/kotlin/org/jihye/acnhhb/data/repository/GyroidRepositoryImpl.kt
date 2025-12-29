package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Gyroid
import org.jihye.acnhhb.domain.repository.GyroidRepository

class GyroidRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : GyroidRepository {

    override fun getGyroids(): Flow<List<Gyroid>> = flow {
        val remoteGyroids = remoteDataSource.fetchGyroids(
            sound = null,
            isExcludeDetails = null,
        )
        emit(remoteGyroids.map { it.toDomain() })
    }
}
