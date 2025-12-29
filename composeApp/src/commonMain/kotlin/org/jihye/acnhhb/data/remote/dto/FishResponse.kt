package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FishResponse(
    val number: Int,
    val name: String,
    @SerialName("image_url")
    val imageUrl: String,
    val location: String,
    @SerialName("shadow_size")
    val shadowSize: String,
    val rarity: String,
    @SerialName("total_catch")
    val totalCatch: Int,
    @SerialName("sell_nook")
    val sellNook: Int,
    @SerialName("sell_cj")
    val sellCj: Int,
    @SerialName("tank_width")
    val tankWidth: Float,
    @SerialName("tank_length")
    val tankLength: Float,
    val time: String,
    val north: Availability,
    val south: Availability,
    val catchphrases: List<String>,
)

@Serializable
data class Availability(
    val availability_array: List<AvailabilityArray>,
    val times_by_month: Map<String, String>,
    val months: String,
    val months_array: List<Int>,
)

@Serializable
data class AvailabilityArray(
    val months: String,
    val time: String,
)
