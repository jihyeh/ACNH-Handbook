package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Bug
import org.jihye.acnhhb.domain.repository.BugRepository

class BugRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val bugNameProvider: BugNameProvider,
) : BugRepository {

    override fun getBugs(): Flow<List<Bug>> = flow {
        coroutineScope {
            val remoteBugsDeferred = async {
                remoteDataSource.fetchBugs(
                    month = null,
                    isExcludeDetails = null,
                    thumbnailSize = null
                )
            }
            async { bugNameProvider.load() }.await()

            val remoteBugs = remoteBugsDeferred.await()

            emit(
                remoteBugs.map {
                    val localizedName = bugNameProvider.getName(it.name)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
