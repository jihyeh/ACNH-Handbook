package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Furniture
import org.jihye.acnhhb.domain.repository.FurnitureRepository

class FurnitureRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val furnitureNameProvider: FurnitureNameProvider,
) : FurnitureRepository {

    override fun getFurniture(
        category: String?,
        color: List<String>?,
    ): Flow<List<Furniture>> = flow {
        coroutineScope {
            val remoteFurnitureDeferred = async {
                remoteDataSource.fetchFurniture(
                    category = category,
                    color = color,
                    isExcludeDetails = null
                )
            }
            async { furnitureNameProvider.load() }.await()

            val remoteFurniture = remoteFurnitureDeferred.await()

            emit(
                remoteFurniture.map {
                    val localizedName = furnitureNameProvider.getName(it.name)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
