package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Clothing

interface ClothingRepository {
    fun getClothing(
        category: String? = null,
        color: List<String>? = null,
        style: List<String>? = null,
        labelTheme: String? = null,
    ): Flow<List<Clothing>>
}
