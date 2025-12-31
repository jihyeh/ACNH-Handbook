package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Event
import org.jihye.acnhhb.domain.repository.EventRepository

class EventRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : EventRepository {
    override fun getEvents(
        year: String?,
        month: String?,
        day: Int?,
    ): Flow<List<Event>> = flow {
        val remoteEvents = remoteDataSource.fetchEvents(
            year = year,
            month = month,
            day = day
        )
        emit(remoteEvents.map { it.toDomain() })
    }
}
