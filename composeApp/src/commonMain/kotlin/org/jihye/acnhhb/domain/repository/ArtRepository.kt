package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Art

interface ArtRepository {
    fun getArts(): Flow<List<Art>>
}
