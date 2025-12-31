package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class EventResponse(
    val event: String,
    val date: String,
    val type: String,
    val url: String,
)
