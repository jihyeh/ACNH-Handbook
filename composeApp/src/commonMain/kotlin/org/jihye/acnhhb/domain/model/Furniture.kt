package org.jihye.acnhhb.domain.model

data class Furniture(
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
    val lucky: Boolean,
    val luckySeason: String,
    val buy: List<Buy>,
    val sell: Int,
    val variationTotal: Int,
    val patternTotal: Int,
    val customizable: Boolean,
    val customKits: Int,
    val customKitType: String,
    val customBodyPart: String,
    val customPatternPart: String,
    val gridWidth: Float,
    val gridLength: Float,
    val height: Float,
    val doorDecor: Boolean,
    val versionAdded: String,
    val unlocked: Boolean,
    val functions: List<String>,
    val availability: List<Availability>,
    val variations: List<Variation>,
) {
    data class Availability(
        val from: String,
        val note: String,
    )

    data class Buy(
        val price: Int,
        val currency: String,
    )

    data class Variation(
        val variation: String,
        val pattern: String,
        val imageUrl: String,
        val colors: List<String>,
    )
}
