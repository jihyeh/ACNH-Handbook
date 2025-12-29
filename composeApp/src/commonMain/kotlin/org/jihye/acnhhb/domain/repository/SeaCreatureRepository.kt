package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.SeaCreature

interface SeaCreatureRepository {
    fun getSeaCreatures(): Flow<List<SeaCreature>>
}
