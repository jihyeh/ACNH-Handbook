package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Interior

interface InteriorRepository {
    fun getInterior(
        category: String? = null,
        color: List<String>? = null,
    ): Flow<List<Interior>>
}
