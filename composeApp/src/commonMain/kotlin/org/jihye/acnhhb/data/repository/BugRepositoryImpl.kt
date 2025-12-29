package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Bug
import org.jihye.acnhhb.domain.repository.BugRepository

class BugRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : BugRepository {

    override fun getBugs(): Flow<List<Bug>> = flow {
        val remoteBugs = remoteDataSource.fetchBugs(
            month = null,
            isExcludeDetails = null,
            thumbnailSize = null
        )
        emit(remoteBugs.map { it.toDomain() })
    }
}
