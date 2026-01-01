package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Villager
import org.jihye.acnhhb.domain.repository.VillagerRepository

class VillagerRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val villagerNameProvider: VillagerNameProvider,
) : VillagerRepository {

    override fun getVillagers(): Flow<List<Villager>> = flow {
        val remoteVillagers = remoteDataSource.fetchVillagers(
            species = null,
            personality = null,
            game = "nh",
            isNhDetails = "true",
        )
        villagerNameProvider.load()
        emit(
            remoteVillagers.map {
                val localizedName = villagerNameProvider.getName(it.id)
                it.toDomain(localizedName)
            }
        )
    }
}
