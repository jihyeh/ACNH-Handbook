package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeaCreatureResponse(
    val number: Int,
    val name: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("shadow_size")
    val shadowSize: String,
    @SerialName("shadow_movement")
    val shadowMovement: String,
    val rarity: String,
    @SerialName("total_catch")
    val totalCatch: Int,
    @SerialName("sell_nook")
    val sellNook: Int,
    @SerialName("tank_width")
    val tankWidth: Float? = null,
    @SerialName("tank_length")
    val tankLength: Float? = null,
    val north: Availability,
    val south: Availability,
    val catchphrases: List<String>,
)
