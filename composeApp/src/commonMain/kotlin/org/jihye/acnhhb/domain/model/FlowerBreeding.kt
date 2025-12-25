package org.jihye.acnhhb.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FlowerBreedingData(
    val breeding: List<FlowerTypeData>,
    val special: List<SpecialBreeding>,
)

@Serializable
data class FlowerTypeData(
    val flowerType: String,
    val rows: List<BreedingRow>,
)

@Serializable
data class BreedingRow(
    val parent1: Flower,
    val parent2: Flower,
    val offspring: Flower,
    val chance: Double,
)

@Serializable
data class Flower(
    val type: FlowerType,
    val color: FlowerColor,
    val fromFlowerBag: Boolean,
)

@Serializable
data class SpecialBreeding(
    val flower: Flower,
    val condition: String?,
    val conditionIconName: String?,
    val offspring: Flower?,
)

enum class FlowerType {
    COSMOS,
    PANSY,
    TULIP,
    ROSE,
    LILY,
    HYACINTH,
    MUM,
    WINDFLOWER,
    LILY_OF_THE_VALLEY,
}

enum class FlowerColor {
    RED,
    ORANGE,
    WHITE,
    BLUE,
    PINK,
    PURPLE,
    BLACK,
    GOLD,
    YELLOW,
    GREEN,
}