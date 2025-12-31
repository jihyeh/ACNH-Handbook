package org.jihye.acnhhb.domain.model

data class Recipe(
    val name: String,
    val url: String,
    val imageUrl: String,
    val serialId: Int,
    val buy: List<Buy>,
    val sell: Int,
    val recipesToUnlock: Int,
    val availability: List<Availability>,
    val materials: List<Material>,
) {
    data class Buy(
        val price: Int,
        val currency: String,
    )

    data class Availability(
        val from: String,
        val note: String,
    )

    data class Material(
        val name: String,
        val count: Int,
    )
}
