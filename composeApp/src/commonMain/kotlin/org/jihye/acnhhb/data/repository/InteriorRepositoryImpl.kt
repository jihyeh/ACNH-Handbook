package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.provider.InteriorNameProvider
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Interior
import org.jihye.acnhhb.domain.repository.InteriorRepository

class InteriorRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val interiorNameProvider: InteriorNameProvider,
) : InteriorRepository {

    override fun getInterior(
        category: String?,
        color: List<String>?,
    ): Flow<List<Interior>> = flow {
        coroutineScope {
            val remoteInteriorDeferred = async {
                remoteDataSource.fetchInterior(
                    category = category,
                    color = color,
                    isExcludeDetails = null
                )
            }
            async { interiorNameProvider.load() }.await()

            val remoteInterior = remoteInteriorDeferred.await()

            emit(
                remoteInterior.map {
                    val localizedName = interiorNameProvider.getName(it.name)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
