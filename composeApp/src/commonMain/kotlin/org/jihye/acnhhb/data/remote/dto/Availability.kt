package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.Serializable

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
