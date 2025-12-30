package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Furniture

interface FurnitureRepository {
    fun getFurniture(
        category: String? = null,
        color: List<String>? = null,
    ): Flow<List<Furniture>>
}
