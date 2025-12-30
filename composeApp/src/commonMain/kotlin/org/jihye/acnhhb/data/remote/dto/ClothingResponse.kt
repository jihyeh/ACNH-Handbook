package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClothingResponse(
    val name: String,
    val url: String,
    val category: String? = null,
    val sell: Int? = null,
    @SerialName("variation_total")
    val variationTotal: Int? = null,
    @SerialName("vill_equip")
    val isVillagerEquippable: Boolean? = null,
    val seasonality: String? = null,
    @SerialName("version_added")
    val versionAdded: String? = null,
    val unlocked: Boolean? = null,
    val notes: String? = null,
    @SerialName("label_themes")
    val labelThemes: List<String>? = null,
    val styles: List<String>? = null,
    val availability: List<Availability>? = null,
    val buy: List<Buy>? = null,
    val variations: List<Variation>? = null,
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

    @Serializable
    data class Variation(
        val variation: String? = null,
        @SerialName("image_url")
        val imageUrl: String,
        val colors: List<String>? = null,
    )
}
