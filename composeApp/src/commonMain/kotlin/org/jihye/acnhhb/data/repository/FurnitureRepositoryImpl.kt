package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Furniture
import org.jihye.acnhhb.domain.repository.FurnitureRepository

class FurnitureRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : FurnitureRepository {

    override fun getFurniture(
        category: String?,
        color: List<String>?,
    ): Flow<List<Furniture>> = flow {
        val remoteFurniture =
            remoteDataSource.fetchFurniture(
                category = category,
                color = color,
                isExcludeDetails = null
            )
        emit(remoteFurniture.map { it.toDomain() })
    }
}
