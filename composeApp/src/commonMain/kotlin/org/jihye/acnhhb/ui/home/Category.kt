package org.jihye.acnhhb.ui.home

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.home_art
import acnhhandbook.composeapp.generated.resources.home_bugs
import acnhhandbook.composeapp.generated.resources.home_clothing
import acnhhandbook.composeapp.generated.resources.home_events
import acnhhandbook.composeapp.generated.resources.home_fish
import acnhhandbook.composeapp.generated.resources.home_flower_breed
import acnhhandbook.composeapp.generated.resources.home_fossils
import acnhhandbook.composeapp.generated.resources.home_furniture
import acnhhandbook.composeapp.generated.resources.home_gyroids
import acnhhandbook.composeapp.generated.resources.home_interior
import acnhhandbook.composeapp.generated.resources.home_items
import acnhhandbook.composeapp.generated.resources.home_photos
import acnhhandbook.composeapp.generated.resources.home_recipes
import acnhhandbook.composeapp.generated.resources.home_sea_creatures
import acnhhandbook.composeapp.generated.resources.home_tools
import acnhhandbook.composeapp.generated.resources.home_villagers
import acnhhandbook.composeapp.generated.resources.ic_home_art
import acnhhandbook.composeapp.generated.resources.ic_home_bug
import acnhhandbook.composeapp.generated.resources.ic_home_clothing
import acnhhandbook.composeapp.generated.resources.ic_home_event
import acnhhandbook.composeapp.generated.resources.ic_home_fish
import acnhhandbook.composeapp.generated.resources.ic_home_flower_breed
import acnhhandbook.composeapp.generated.resources.ic_home_fossil
import acnhhandbook.composeapp.generated.resources.ic_home_furniture
import acnhhandbook.composeapp.generated.resources.ic_home_gyroid
import acnhhandbook.composeapp.generated.resources.ic_home_interior
import acnhhandbook.composeapp.generated.resources.ic_home_item
import acnhhandbook.composeapp.generated.resources.ic_home_photo
import acnhhandbook.composeapp.generated.resources.ic_home_recipe
import acnhhandbook.composeapp.generated.resources.ic_home_sea
import acnhhandbook.composeapp.generated.resources.ic_home_tool
import acnhhandbook.composeapp.generated.resources.ic_home_villager
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Category(
    val title: StringResource,
    val icon: DrawableResource,
    val color: Color,
) {
    VILLAGERS(
        title = Res.string.home_villagers,
        icon = Res.drawable.ic_home_villager,
        color = Color(0xFF4CAF50),
    ),
    BUGS(
        title = Res.string.home_bugs,
        icon = Res.drawable.ic_home_bug,
        color = Color(0xFF795548),
    ),
    FISH(
        title = Res.string.home_fish,
        icon = Res.drawable.ic_home_fish,
        color = Color(0xFF2196F3),
    ),
    SEA_CREATURES(
        title = Res.string.home_sea_creatures,
        icon = Res.drawable.ic_home_sea,
        color = Color(0xFF00BCD4),
    ),
    ART(
        title = Res.string.home_art,
        icon = Res.drawable.ic_home_art,
        color = Color(0xFF964B00),
    ),
    FOSSILS(
        title = Res.string.home_fossils,
        icon = Res.drawable.ic_home_fossil,
        color = Color(0xFF8BC34A),
    ),
    GYROIDS(
        title = Res.string.home_gyroids,
        icon = Res.drawable.ic_home_gyroid,
        color = Color(0xFFFF5722),
    ),
    ITEMS(
        title = Res.string.home_items,
        icon = Res.drawable.ic_home_item,
        color = Color(0xFFE91E63),
    ),
    TOOLS(
        title = Res.string.home_tools,
        icon = Res.drawable.ic_home_tool,
        color = Color(0xFF9E9E9E),
    ),
    CLOTHING(
        title = Res.string.home_clothing,
        icon = Res.drawable.ic_home_clothing,
        color = Color(0xFFF44336),
    ),
    FURNITURE(
        title = Res.string.home_furniture,
        icon = Res.drawable.ic_home_furniture,
        color = Color(0xFF9C27B0),
    ),
    INTERIOR(
        title = Res.string.home_interior,
        icon = Res.drawable.ic_home_interior,
        color = Color(0xFF3F51B5),
    ),
    RECIPES(
        title = Res.string.home_recipes,
        icon = Res.drawable.ic_home_recipe,
        color = Color(0xFFCDDC39),
    ),
    EVENTS(
        title = Res.string.home_events,
        icon = Res.drawable.ic_home_event,
        color = Color(0xFFFFC107),
    ),
    PHOTOS(
        title = Res.string.home_photos,
        icon = Res.drawable.ic_home_photo,
        color = Color(0xFF009688),
    ),
    FLOWER_BREED(
        title = Res.string.home_flower_breed,
        icon = Res.drawable.ic_home_flower_breed,
        color = Color(0xFFFF9800),
    ),
}
