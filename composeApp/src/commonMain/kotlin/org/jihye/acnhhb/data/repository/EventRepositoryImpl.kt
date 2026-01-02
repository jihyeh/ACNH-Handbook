package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.parseHemisphere
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Event
import org.jihye.acnhhb.domain.repository.EventRepository

class EventRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val eventNameProvider: EventNameProvider,
) : EventRepository {
    override fun getEvents(
        year: String?,
        month: String?,
        day: Int?,
    ): Flow<List<Event>> = flow {
        coroutineScope {
            val remoteEventsDeferred = async {
                remoteDataSource.fetchEvents(year = year, month = month, day = day)
            }
            async { eventNameProvider.load() }.await()

            val remoteEvents = remoteEventsDeferred.await()

            emit(
                remoteEvents.map {
                    val (cleanName, _) = parseHemisphere(it.event)
                    val localizedName = eventNameProvider.getName(cleanName, it.type)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
