package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Clothing
import org.jihye.acnhhb.domain.repository.ClothingRepository

class ClothingRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : ClothingRepository {

    override fun getClothing(
        category: String?,
        color: List<String>?,
        style: List<String>?,
        labelTheme: String?,
    ): Flow<List<Clothing>> = flow {
        val remoteClothing =
            remoteDataSource.fetchClothing(
                category = category,
                color = color,
                style = style,
                labelTheme = labelTheme,
                isExcludeDetails = null,
            )
        emit(remoteClothing.map { it.toDomain() })
    }
}
