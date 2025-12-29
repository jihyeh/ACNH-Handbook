package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GyroidResponse(
    val name: String,
    val url: String,
    @SerialName("hha_base")
    val hhaBase: Int? = null,
    val sell: Int? = null,
    val customizable: Boolean? = null,
    @SerialName("custom_kits")
    val customKits: Int? = null,
    @SerialName("custom_kit_type")
    val customKitType: String? = null,
    @SerialName("custom_body_part")
    val customBodyPart: String? = null,
    @SerialName("cyrus_price")
    val cyrusPrice: Int? = null,
    @SerialName("variation_total")
    val variationTotal: Int? = null,
    @SerialName("grid_width")
    val gridWidth: Double? = null,
    @SerialName("grid_length")
    val gridLength: Double? = null,
    val sound: String? = null,
    @SerialName("version_added")
    val versionAdded: String? = null,
    val unlocked: Boolean? = null,
    val notes: String? = null,
    val availability: List<GyroidAvailability>? = null,
    val buy: List<GyroidBuy>? = null,
    val variations: List<GyroidVariation>? = null,
)

@Serializable
data class GyroidAvailability(
    val from: String? = null,
    val note: String? = null,
)

@Serializable
data class GyroidBuy(
    val price: Int? = null,
    val currency: String? = null,
)

@Serializable
data class GyroidVariation(
    val variation: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    val colors: List<String>? = null,
)
