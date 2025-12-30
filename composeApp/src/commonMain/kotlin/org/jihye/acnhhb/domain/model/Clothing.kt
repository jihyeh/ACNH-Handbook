package org.jihye.acnhhb.domain.model

data class Clothing(
    val name: String,
    val url: String,
    val imageUrl: String,
    val category: String,
    val sell: Int,
    val variationTotal: Int,
    val isVillagerEquippable: Boolean,
    val seasonality: String,
    val versionAdded: String,
    val unlocked: Boolean,
    val notes: String,
    val labelThemes: List<String>,
    val styles: List<String>,
    val availability: List<Availability>,
    val buy: List<Buy>,
    val variations: List<Variation>,
) {
    data class Variation(
        val variation: String,
        val imageUrl: String,
        val colors: List<String>,
    )
}
