package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    val name: String,
    val url: String,
    val category: String,
    val sell: Int? = null,
    val customizable: Boolean? = null,
    @SerialName("custom_kits")
    val customKits: Int? = null,
    @SerialName("custom_body_part")
    val customBodyPart: String? = null,
    val interactable: Boolean? = null,
    @SerialName("version_added")
    val versionAdded: String? = null,
    val unlocked: Boolean? = null,
    @SerialName("grid_width")
    val gridWidth: Float? = null,
    @SerialName("grid_length")
    val gridLength: Float? = null,
    val availability: List<Availability>? = null,
    val buy: List<Buy>? = null,
    val variations: List<Variation>? = null,
) {
    @Serializable
    data class Buy(
        val price: Int? = null,
        val currency: String? = null,
    )

    @Serializable
    data class Availability(
        val from: String? = null,
        val note: String? = null,
    )

    @Serializable
    data class Variation(
        val variation: String? = null,
        @SerialName("image_url")
        val imageUrl: String? = null,
        val colors: List<String>? = null,
    )
}
