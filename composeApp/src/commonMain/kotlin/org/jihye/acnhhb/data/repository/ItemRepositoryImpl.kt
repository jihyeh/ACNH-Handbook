package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Item
import org.jihye.acnhhb.domain.repository.ItemRepository

class ItemRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : ItemRepository {

    override fun getItems(): Flow<List<Item>> = flow {
        val remoteItems = remoteDataSource.fetchItems(
            thumbnailSize = null,
            isExcludeDetails = null
        )
        emit(remoteItems.map { it.toDomain() })
    }
}
