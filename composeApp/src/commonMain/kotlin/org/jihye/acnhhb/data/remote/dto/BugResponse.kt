package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BugResponse(
    val number: Int,
    val name: String,
    val url: String? = null,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("render_url")
    val renderUrl: String? = null,
    val location: String,
    val weather: String? = null,
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
