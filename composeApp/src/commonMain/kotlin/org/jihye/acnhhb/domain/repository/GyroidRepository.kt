package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Gyroid

interface GyroidRepository {
    fun getGyroids(): Flow<List<Gyroid>>
}
