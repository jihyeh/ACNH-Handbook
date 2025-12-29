package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Availability(
    @SerialName("availability_array")
    val availabilities: List<AvailabilityArray>,
    @SerialName("times_by_month")
    val timesByMonth: Map<String, String>,
    val months: String,
    @SerialName("months_array")
    val monthIndices: List<Int>,
)

@Serializable
data class AvailabilityArray(
    val months: String,
    val time: String,
)
