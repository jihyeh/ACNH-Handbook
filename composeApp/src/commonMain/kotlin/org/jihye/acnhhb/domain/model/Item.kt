package org.jihye.acnhhb.domain.model

data class Item(
    val name: String,
    val url: String,
    val imageUrl: String,
    val stack: Int,
    val hhaBase: Int,
    val sell: Int,
    val isFence: Boolean,
    val materialType: String,
    val materialSeasonality: String,
    val materialSort: Int,
    val materialNameSort: Int,
    val materialSeasonalitySort: Int,
    val edible: Boolean,
    val plantType: String,
    val versionAdded: String,
    val unlocked: Boolean,
    val notes: String,
    val availability: List<Availability>,
    val buy: List<Buy>,
) {
    val category: ItemCategory
        get() = when {
            materialType.isNotEmpty() -> {
                ItemCategory.entries.find {
                    it.dbValue?.equals(materialType, ignoreCase = true) == true
                } ?: ItemCategory.OTHER
            }
            isFence -> {
                ItemCategory.FENCE
            }
            plantType.isNotEmpty() -> {
                ItemCategory.entries.find {
                    it.dbValue?.equals(plantType, ignoreCase = true) == true
                } ?: ItemCategory.OTHER
            }
            else -> ItemCategory.OTHER
        }
}

data class Availability(
    val from: String,
    val note: String,
)

data class Buy(
    val price: Int,
    val currency: String,
)
