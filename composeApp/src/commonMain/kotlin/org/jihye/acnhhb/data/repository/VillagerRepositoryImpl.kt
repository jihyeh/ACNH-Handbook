package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.provider.VillagerNameProvider
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Villager
import org.jihye.acnhhb.domain.repository.VillagerRepository

class VillagerRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val villagerNameProvider: VillagerNameProvider,
) : VillagerRepository {

    override fun getVillagers(): Flow<List<Villager>> = flow {
        coroutineScope {
            val remoteVillagersDeferred = async {
                remoteDataSource.fetchVillagers(
                    species = null,
                    personality = null,
                    game = "nh",
                    isNhDetails = "true",
                )
            }
            async { villagerNameProvider.load() }.await()
            val remoteVillagers = remoteVillagersDeferred.await()

            emit(
                remoteVillagers.map {
                    val localizedName = villagerNameProvider.getName(it.name)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
