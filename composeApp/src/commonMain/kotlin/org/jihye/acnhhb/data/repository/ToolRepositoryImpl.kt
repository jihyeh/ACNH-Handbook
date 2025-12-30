package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Tool
import org.jihye.acnhhb.domain.repository.ToolRepository

class ToolRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : ToolRepository {

    override fun getTools(): Flow<List<Tool>> = flow {
        val remoteTools = remoteDataSource.fetchTools(
            isExcludeDetails = null,
        )
        emit(remoteTools.map { it.toDomain() })
    }
}
