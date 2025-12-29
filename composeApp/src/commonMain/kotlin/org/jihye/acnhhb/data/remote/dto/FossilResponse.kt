package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FossilResponse(
    val name: String,
    val url: String? = null,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("sell")
    val price: Int,
    @SerialName("fossil_group")
    val fossilGroup: String,
    val description: String? = null,
    val interactable: Boolean? = null,
    @SerialName("hha_base")
    val hhaBase: Int? = null,
    val width: Int? = null,
    val length: Int? = null,
    val colors: List<String>? = null,
)
