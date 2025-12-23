package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Villager

interface VillagerRepository {
    fun getVillagers(): Flow<List<Villager>>
}
