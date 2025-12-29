package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemResponse(
    val name: String,
    val url: String,
    @SerialName("image_url")
    val imageUrl: String,
    val stack: Int? = null,
    @SerialName("hha_base")
    val hhaBase: Int? = null,
    val sell: Int? = null,
    @SerialName("is_fence")
    val isFence: Boolean? = null,
    @SerialName("material_type")
    val materialType: String? = null,
    @SerialName("material_seasonality")
    val materialSeasonality: String? = null,
    @SerialName("material_sort")
    val materialSort: Int? = null,
    @SerialName("material_name_sort")
    val materialNameSort: Int? = null,
    @SerialName("material_seasonality_sort")
    val materialSeasonalitySort: Int? = null,
    val edible: Boolean? = null,
    @SerialName("plant_type")
    val plantType: String? = null,
    @SerialName("version_added")
    val versionAdded: String? = null,
    val unlocked: Boolean? = null,
    val notes: String? = null,
    val itemAvailability: List<ItemAvailability>? = null,
    val buy: List<Buy>? = null,
)

@Serializable
data class ItemAvailability(
    val from: String? = null,
    val note: String? = null,
)

@Serializable
data class Buy(
    val price: Int? = null,
    val currency: String? = null,
)
