package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Fish
import org.jihye.acnhhb.domain.repository.FishRepository

class FishRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : FishRepository {

    override fun getFishes(): Flow<List<Fish>> = flow {
        val remoteFish =
            remoteDataSource.fetchFishes(
                month = null,
                isExcludeDetails = null,
                thumbnailSize = null
            )
        emit(remoteFish.map { it.toDomain() })
    }
}
