package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Bug

interface BugRepository {
    fun getBugs(): Flow<List<Bug>>
}
