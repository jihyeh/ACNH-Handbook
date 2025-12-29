package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Fossil
import org.jihye.acnhhb.domain.repository.FossilRepository

class FossilRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : FossilRepository {

    override fun getFossils(): Flow<List<Fossil>> = flow {
        val remoteFossils = remoteDataSource.fetchFossils(
            thumbnailSize = null
        )
        emit(remoteFossils.map { it.toDomain() })
    }
}
