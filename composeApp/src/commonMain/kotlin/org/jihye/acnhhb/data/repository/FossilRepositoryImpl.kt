package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Fossil
import org.jihye.acnhhb.domain.repository.FossilRepository

class FossilRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val fossilNameProvider: FossilNameProvider,
) : FossilRepository {

    override fun getFossils(): Flow<List<Fossil>> = flow {
        coroutineScope {
            val remoteFossilsDeferred = async {
                remoteDataSource.fetchFossils(thumbnailSize = null)
            }
            async { fossilNameProvider.load() }.await()

            val remoteFossils = remoteFossilsDeferred.await()

            emit(
                remoteFossils.map {
                    val localizedName = fossilNameProvider.getName(it.name)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
