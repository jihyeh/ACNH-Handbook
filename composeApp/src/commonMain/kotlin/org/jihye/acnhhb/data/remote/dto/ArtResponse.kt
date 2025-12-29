package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtResponse(
    val name: String,
    val url: String? = null,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("has_fake")
    val hasFake: Boolean? = null,
    val buy: Int? = null,
    val sell: Int? = null,
    @SerialName("art_name")
    val artName: String? = null,
    @SerialName("art_type")
    val artType: String? = null,
    val year: String? = null,
    @SerialName("art_style")
    val artStyle: String? = null,
    val availability: String? = null,
    val width: Double? = null,
    val length: Double? = null,
    val author: String? = null,
    val description: String? = null,
    @SerialName("real_info")
    val realInfo: ArtInfo? = null,
    @SerialName("fake_info")
    val fakeInfo: ArtInfo? = null,
)

@Serializable
data class ArtInfo(
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("texture_url")
    val textureUrl: String? = null,
    val description: String? = null,
)
