package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Fossil

interface FossilRepository {
    fun getFossils(): Flow<List<Fossil>>
}
