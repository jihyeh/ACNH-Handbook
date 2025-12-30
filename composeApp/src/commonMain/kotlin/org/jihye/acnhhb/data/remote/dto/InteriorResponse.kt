package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InteriorResponse(
    val name: String,
    val url: String,
    @SerialName("image_url")
    val imageUrl: String? = null,
    val category: String? = null,
    @SerialName("item_series")
    val itemSeries: String? = null,
    @SerialName("item_set")
    val itemSet: String? = null,
    val themes: List<String>? = null,
    @SerialName("hha_category")
    val hhaCategory: String? = null,
    @SerialName("hha_base")
    val hhaBase: Int? = null,
    val tag: String? = null,
    val sell: Int? = null,
    @SerialName("version_added")
    val versionAdded: String? = null,
    val unlocked: Boolean? = null,
    val notes: String? = null,
    @SerialName("grid_width")
    val gridWidth: Float? = null,
    @SerialName("grid_length")
    val gridLength: Float? = null,
    val colors: List<String>? = null,
    val availability: List<Availability>? = null,
    val buy: List<Buy>? = null,
) {
    @Serializable
    data class Availability(
        val from: String? = null,
        val note: String? = null,
    )

    @Serializable
    data class Buy(
        val price: Int? = null,
        val currency: String? = null,
    )
}
