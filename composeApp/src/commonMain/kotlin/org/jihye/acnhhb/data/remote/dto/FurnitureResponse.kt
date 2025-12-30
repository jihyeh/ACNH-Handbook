package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FurnitureResponse(
    val name: String,
    val url: String,
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
    val lucky: Boolean? = null,
    @SerialName("lucky_season")
    val luckySeason: String? = null,
    val buy: List<Buy>? = null,
    val sell: Int? = null,
    @SerialName("variation_total")
    val variationTotal: Int? = null,
    @SerialName("pattern_total")
    val patternTotal: Int? = null,
    val customizable: Boolean? = null,
    @SerialName("custom_kits")
    val customKits: Int? = null,
    @SerialName("custom_kit_type")
    val customKitType: String? = null,
    @SerialName("custom_body_part")
    val customBodyPart: String? = null,
    @SerialName("custom_pattern_part")
    val customPatternPart: String? = null,
    @SerialName("grid_width")
    val gridWidth: Float? = null,
    @SerialName("grid_length")
    val gridLength: Float? = null,
    val height: Float? = null,
    @SerialName("door_decor")
    val doorDecor: Boolean? = null,
    @SerialName("version_added")
    val versionAdded: String? = null,
    val unlocked: Boolean? = null,
    val functions: List<String>? = null,
    val availability: List<Availability>? = null,
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
        val pattern: String? = null,
        @SerialName("image_url")
        val imageUrl: String,
        val colors: List<String>? = null,
    )
}
