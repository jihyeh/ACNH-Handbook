package org.jihye.acnhhb.domain.model

data class Interior(
    val name: String,
    val url: String,
    val imageUrl: String,
    val category: String,
    val itemSeries: String,
    val itemSet: String,
    val themes: List<String>,
    val hhaCategory: String,
    val hhaBase: Int,
    val tag: String,
    val sell: Int,
    val versionAdded: String,
    val unlocked: Boolean,
    val notes: String,
    val gridWidth: Float,
    val gridLength: Float,
    val colors: List<String>,
    val availability: List<Availability>,
    val buy: List<Buy>,
) {
    data class Availability(
        val from: String,
        val note: String,
    )

    data class Buy(
        val price: Int,
        val currency: String,
    )
}
