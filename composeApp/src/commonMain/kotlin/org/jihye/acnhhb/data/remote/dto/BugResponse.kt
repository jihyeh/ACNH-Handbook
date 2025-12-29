package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BugResponse(
    val number: Int,
    val name: String,
    @SerialName("image_url")
    val imageUrl: String,
    val location: String,
    val rarity: String,
    @SerialName("total_catch")
    val totalCatch: Int,
    @SerialName("sell_nook")
    val sellNook: Int,
    @SerialName("sell_flick")
    val sellFlick: Int,
    @SerialName("tank_width")
    val tankWidth: Float? = null,
    @SerialName("tank_length")
    val tankLength: Float? = null,
    val north: Availability,
    val south: Availability,
    val catchphrases: List<String>,
)
