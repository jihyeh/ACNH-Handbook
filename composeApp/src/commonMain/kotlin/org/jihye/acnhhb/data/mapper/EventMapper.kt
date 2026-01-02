package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.EventResponse
import org.jihye.acnhhb.domain.model.Event

fun EventResponse.toDomain(localizedName: String? = null): Event {
    val (cleanedName, hemisphere) = parseHemisphere(event)
    return Event(
        name = localizedName ?: cleanedName,
        date = date,
        type = parseType(type),
        url = url,
        hemisphere = hemisphere
    )
}

fun parseType(type: String): Event.EventType {
    return when (type) {
        "Event" -> Event.EventType.EVENT
        "Nook Shopping" -> Event.EventType.NOOK_SHOPPING
        "Recipes" -> Event.EventType.RECIPES
        "Birthday" -> Event.EventType.BIRTHDAY
        "Season" -> Event.EventType.SEASON
        "Shopping season" -> Event.EventType.SHOPPING_SEASON
        else -> Event.EventType.UNKNOWN
    }
}

fun parseHemisphere(eventName: String): Pair<String, Event.HemisphereType> {
    return when {
        eventName.contains("(Northern Hemisphere)") -> {
            eventName.replace("(Northern Hemisphere)", "").trim() to Event.HemisphereType.NORTHERN
        }

        eventName.contains("(Southern Hemisphere)") -> {
            eventName.replace("(Southern Hemisphere)", "").trim() to Event.HemisphereType.SOUTHERN
        }

        else -> {
            eventName to Event.HemisphereType.BOTH
        }
    }
}
