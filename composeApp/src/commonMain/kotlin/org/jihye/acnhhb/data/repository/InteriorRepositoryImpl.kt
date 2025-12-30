package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Interior
import org.jihye.acnhhb.domain.repository.InteriorRepository

class InteriorRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : InteriorRepository {

    override fun getInterior(
        category: String?,
        color: List<String>?,
    ): Flow<List<Interior>> = flow {
        val remoteInterior = remoteDataSource.fetchInterior(
            category = category,
            color = color,
            isExcludeDetails = null
        )
        emit(remoteInterior.map { it.toDomain() })
    }
}
