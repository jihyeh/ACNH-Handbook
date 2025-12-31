package org.jihye.acnhhb.domain.model

data class Event(
    val name: String,
    val date: String,
    val type: EventType,
    val url: String,
    val hemisphere: HemisphereType,
) {
    enum class EventType {
        EVENT,
        NOOK_SHOPPING,
        RECIPES,
        BIRTHDAY,
        SEASON,
        SHOPPING_SEASON,
        UNKNOWN
    }

    enum class HemisphereType {
        NORTHERN,
        SOUTHERN,
        BOTH
    }
}
