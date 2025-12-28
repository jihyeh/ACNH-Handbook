package org.jihye.acnhhb.ui.flowerbreeding

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.ic_cosmos_black_100
import acnhhandbook.composeapp.generated.resources.ic_cosmos_orange_100
import acnhhandbook.composeapp.generated.resources.ic_cosmos_pink_100
import acnhhandbook.composeapp.generated.resources.ic_cosmos_red_100
import acnhhandbook.composeapp.generated.resources.ic_cosmos_white_100
import acnhhandbook.composeapp.generated.resources.ic_cosmos_yellow_100
import acnhhandbook.composeapp.generated.resources.ic_golden_watering_can_100
import acnhhandbook.composeapp.generated.resources.ic_hyacinth_blue_100
import acnhhandbook.composeapp.generated.resources.ic_hyacinth_orange_100
import acnhhandbook.composeapp.generated.resources.ic_hyacinth_pink_100
import acnhhandbook.composeapp.generated.resources.ic_hyacinth_purple_100
import acnhhandbook.composeapp.generated.resources.ic_hyacinth_red_100
import acnhhandbook.composeapp.generated.resources.ic_hyacinth_white_100
import acnhhandbook.composeapp.generated.resources.ic_hyacinth_yellow_100
import acnhhandbook.composeapp.generated.resources.ic_lily_black_100
import acnhhandbook.composeapp.generated.resources.ic_lily_of_the_valley_white_100
import acnhhandbook.composeapp.generated.resources.ic_lily_orange_100
import acnhhandbook.composeapp.generated.resources.ic_lily_pink_100
import acnhhandbook.composeapp.generated.resources.ic_lily_red_100
import acnhhandbook.composeapp.generated.resources.ic_lily_white_100
import acnhhandbook.composeapp.generated.resources.ic_lily_yellow_100
import acnhhandbook.composeapp.generated.resources.ic_mum_green_100
import acnhhandbook.composeapp.generated.resources.ic_mum_pink_100
import acnhhandbook.composeapp.generated.resources.ic_mum_purple_100
import acnhhandbook.composeapp.generated.resources.ic_mum_red_100
import acnhhandbook.composeapp.generated.resources.ic_mum_white_100
import acnhhandbook.composeapp.generated.resources.ic_mum_yellow_100
import acnhhandbook.composeapp.generated.resources.ic_pansy_blue_100
import acnhhandbook.composeapp.generated.resources.ic_pansy_orange_100
import acnhhandbook.composeapp.generated.resources.ic_pansy_purple_100
import acnhhandbook.composeapp.generated.resources.ic_pansy_red_100
import acnhhandbook.composeapp.generated.resources.ic_pansy_white_100
import acnhhandbook.composeapp.generated.resources.ic_pansy_yellow_100
import acnhhandbook.composeapp.generated.resources.ic_rose_black_100
import acnhhandbook.composeapp.generated.resources.ic_rose_blue_100
import acnhhandbook.composeapp.generated.resources.ic_rose_gold_100
import acnhhandbook.composeapp.generated.resources.ic_rose_orange_100
import acnhhandbook.composeapp.generated.resources.ic_rose_pink_100
import acnhhandbook.composeapp.generated.resources.ic_rose_purple_100
import acnhhandbook.composeapp.generated.resources.ic_rose_red_100
import acnhhandbook.composeapp.generated.resources.ic_rose_white_100
import acnhhandbook.composeapp.generated.resources.ic_rose_yellow_100
import acnhhandbook.composeapp.generated.resources.ic_tulip_black_100
import acnhhandbook.composeapp.generated.resources.ic_tulip_orange_100
import acnhhandbook.composeapp.generated.resources.ic_tulip_pink_100
import acnhhandbook.composeapp.generated.resources.ic_tulip_purple_100
import acnhhandbook.composeapp.generated.resources.ic_tulip_red_100
import acnhhandbook.composeapp.generated.resources.ic_tulip_white_100
import acnhhandbook.composeapp.generated.resources.ic_tulip_yellow_100
import acnhhandbook.composeapp.generated.resources.ic_windflower_blue_100
import acnhhandbook.composeapp.generated.resources.ic_windflower_orange_100
import acnhhandbook.composeapp.generated.resources.ic_windflower_pink_100
import acnhhandbook.composeapp.generated.resources.ic_windflower_purple_100
import acnhhandbook.composeapp.generated.resources.ic_windflower_red_100
import acnhhandbook.composeapp.generated.resources.ic_windflower_white_100
import org.jetbrains.compose.resources.DrawableResource
import org.jihye.acnhhb.domain.model.Flower
import org.jihye.acnhhb.domain.model.SpecialBreeding

val Flower.iconRes: DrawableResource?
    get() {
        val typeName = type.name.lowercase()
        val colorName = color.name.lowercase()

        if (typeName == "lily_of_the_valley") {
            return Res.drawable.ic_lily_of_the_valley_white_100
        }
        return findFlowerResource(typeName, colorName)
    }

val SpecialBreeding.conditionIcon: DrawableResource?
    get() {
        return if ("golden_watering_can" == conditionIconName) {
            Res.drawable.ic_golden_watering_can_100
        } else {
            null
        }
    }

private fun findFlowerResource(type: String, color: String): DrawableResource? {
    val key = "${type}_$color"
    return flowerResourceMap[key]
}

private val flowerResourceMap = mapOf(
    "cosmos_black" to Res.drawable.ic_cosmos_black_100,
    "cosmos_orange" to Res.drawable.ic_cosmos_orange_100,
    "cosmos_pink" to Res.drawable.ic_cosmos_pink_100,
    "cosmos_red" to Res.drawable.ic_cosmos_red_100,
    "cosmos_white" to Res.drawable.ic_cosmos_white_100,
    "cosmos_yellow" to Res.drawable.ic_cosmos_yellow_100,

    "hyacinth_blue" to Res.drawable.ic_hyacinth_blue_100,
    "hyacinth_orange" to Res.drawable.ic_hyacinth_orange_100,
    "hyacinth_pink" to Res.drawable.ic_hyacinth_pink_100,
    "hyacinth_purple" to Res.drawable.ic_hyacinth_purple_100,
    "hyacinth_red" to Res.drawable.ic_hyacinth_red_100,
    "hyacinth_white" to Res.drawable.ic_hyacinth_white_100,
    "hyacinth_yellow" to Res.drawable.ic_hyacinth_yellow_100,

    "lily_black" to Res.drawable.ic_lily_black_100,
    "lily_orange" to Res.drawable.ic_lily_orange_100,
    "lily_pink" to Res.drawable.ic_lily_pink_100,
    "lily_red" to Res.drawable.ic_lily_red_100,
    "lily_white" to Res.drawable.ic_lily_white_100,
    "lily_yellow" to Res.drawable.ic_lily_yellow_100,
    "lily_of_the_valley_white" to Res.drawable.ic_lily_of_the_valley_white_100,

    "mum_green" to Res.drawable.ic_mum_green_100,
    "mum_pink" to Res.drawable.ic_mum_pink_100,
    "mum_purple" to Res.drawable.ic_mum_purple_100,
    "mum_red" to Res.drawable.ic_mum_red_100,
    "mum_white" to Res.drawable.ic_mum_white_100,
    "mum_yellow" to Res.drawable.ic_mum_yellow_100,

    "pansy_blue" to Res.drawable.ic_pansy_blue_100,
    "pansy_orange" to Res.drawable.ic_pansy_orange_100,
    "pansy_purple" to Res.drawable.ic_pansy_purple_100,
    "pansy_red" to Res.drawable.ic_pansy_red_100,
    "pansy_white" to Res.drawable.ic_pansy_white_100,
    "pansy_yellow" to Res.drawable.ic_pansy_yellow_100,

    "rose_black" to Res.drawable.ic_rose_black_100,
    "rose_blue" to Res.drawable.ic_rose_blue_100,
    "rose_gold" to Res.drawable.ic_rose_gold_100,
    "rose_orange" to Res.drawable.ic_rose_orange_100,
    "rose_pink" to Res.drawable.ic_rose_pink_100,
    "rose_purple" to Res.drawable.ic_rose_purple_100,
    "rose_red" to Res.drawable.ic_rose_red_100,
    "rose_white" to Res.drawable.ic_rose_white_100,
    "rose_yellow" to Res.drawable.ic_rose_yellow_100,

    "tulip_black" to Res.drawable.ic_tulip_black_100,
    "tulip_orange" to Res.drawable.ic_tulip_orange_100,
    "tulip_pink" to Res.drawable.ic_tulip_pink_100,
    "tulip_purple" to Res.drawable.ic_tulip_purple_100,
    "tulip_red" to Res.drawable.ic_tulip_red_100,
    "tulip_white" to Res.drawable.ic_tulip_white_100,
    "tulip_yellow" to Res.drawable.ic_tulip_yellow_100,

    "windflower_blue" to Res.drawable.ic_windflower_blue_100,
    "windflower_orange" to Res.drawable.ic_windflower_orange_100,
    "windflower_pink" to Res.drawable.ic_windflower_pink_100,
    "windflower_purple" to Res.drawable.ic_windflower_purple_100,
    "windflower_red" to Res.drawable.ic_windflower_red_100,
    "windflower_white" to Res.drawable.ic_windflower_white_100,
)