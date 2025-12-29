package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtResponse(
    val name: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("has_fake")
    val hasFake: Boolean,
    val buy: Int,
    val sell: Int,
    @SerialName("art_name")
    val artName: String,
    val author: String,
    val description: String,
)
