package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Tool

interface ToolRepository {
    fun getTools(): Flow<List<Tool>>
}
