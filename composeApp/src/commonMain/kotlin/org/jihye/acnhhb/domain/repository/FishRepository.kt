package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Fish

interface FishRepository {
    fun getFishes(): Flow<List<Fish>>
}
