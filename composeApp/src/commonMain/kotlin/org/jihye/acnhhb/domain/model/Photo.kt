package org.jihye.acnhhb.domain.model

data class Photo(
    val name: String,
    val url: String,
    val category: String,
    val imageUrl: String,
    val sell: Int,
    val buy: List<Buy>,
    val availability: List<Availability>,
) {
    data class Buy(
        val price: Int,
        val currency: String,
    )

    data class Availability(
        val from: String,
        val note: String,
    )
}
