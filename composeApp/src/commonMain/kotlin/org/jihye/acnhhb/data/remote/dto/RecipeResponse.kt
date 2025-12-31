package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(
    val name: String,
    val url: String,
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("serial_id")
    val serialId: Int? = null,
    val buy: List<Buy>? = null,
    val sell: Int? = null,
    @SerialName("recipes_to_unlock")
    val recipesToUnlock: Int? = null,
    val availability: List<Availability>? = null,
    val materials: List<Material>? = null,
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
    data class Material(
        val name: String,
        val count: Int,
    )
}
