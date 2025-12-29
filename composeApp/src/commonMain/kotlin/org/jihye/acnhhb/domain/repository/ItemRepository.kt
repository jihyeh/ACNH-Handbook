package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Item

interface ItemRepository {
    fun getItems(): Flow<List<Item>>
}
