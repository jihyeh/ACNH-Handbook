package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FossilResponse(
    val name: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("sell")
    val price: Int,
    @SerialName("fossil_group")
    val fossilGroup: String,
    val description: String? = null,
)
