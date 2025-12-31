package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Event

interface EventRepository {
    fun getEvents(
        year: String? = null,
        month: String? = null,
        day: Int? = null,
    ): Flow<List<Event>>
}
