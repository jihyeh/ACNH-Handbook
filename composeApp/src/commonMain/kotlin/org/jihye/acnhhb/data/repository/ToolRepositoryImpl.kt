package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.provider.ToolNameProvider
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Tool
import org.jihye.acnhhb.domain.repository.ToolRepository

class ToolRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val toolNameProvider: ToolNameProvider,
) : ToolRepository {

    override fun getTools(): Flow<List<Tool>> = flow {
        coroutineScope {
            val remoteToolsDeferred = async {
                remoteDataSource.fetchTools(
                    isExcludeDetails = null,
                )
            }
            async { toolNameProvider.load() }.await()

            val remoteTools = remoteToolsDeferred.await()

            emit(
                remoteTools.map {
                    val localizedName = toolNameProvider.getName(it.name)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
