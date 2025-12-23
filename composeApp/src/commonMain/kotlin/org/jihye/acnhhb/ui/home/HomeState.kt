package org.jihye.acnhhb.ui.home

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.home_art
import acnhhandbook.composeapp.generated.resources.home_bugs
import acnhhandbook.composeapp.generated.resources.home_events
import acnhhandbook.composeapp.generated.resources.home_fish
import acnhhandbook.composeapp.generated.resources.home_flower_breed
import acnhhandbook.composeapp.generated.resources.home_fossils
import acnhhandbook.composeapp.generated.resources.home_items
import acnhhandbook.composeapp.generated.resources.home_sea_creatures
import acnhhandbook.composeapp.generated.resources.home_villagers
import acnhhandbook.composeapp.generated.resources.ic_artwork_150
import acnhhandbook.composeapp.generated.resources.ic_bug_150
import acnhhandbook.composeapp.generated.resources.ic_event_150
import acnhhandbook.composeapp.generated.resources.ic_fish_150
import acnhhandbook.composeapp.generated.resources.ic_flower_breed_150
import acnhhandbook.composeapp.generated.resources.ic_fossil_150
import acnhhandbook.composeapp.generated.resources.ic_item_150
import acnhhandbook.composeapp.generated.resources.ic_sea_150
import acnhhandbook.composeapp.generated.resources.ic_villager_150
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class HomeItem(
    val title: StringResource,
    val icon: DrawableResource,
    val color: Color,
    val route: String,
)

val homeItems = listOf(
    HomeItem(
        title = Res.string.home_villagers,
        icon = Res.drawable.ic_villager_150,
        color = Color(0xFF4CAF50),
        route = "villagers"
    ),
    HomeItem(
        title = Res.string.home_bugs,
        icon = Res.drawable.ic_bug_150,
        color = Color(0xFF8BC34A),
        route = "bugs"
    ),
    HomeItem(
        title = Res.string.home_fish,
        icon = Res.drawable.ic_fish_150,
        color = Color(0xFF2196F3),
        route = "fish"
    ),
    HomeItem(
        title = Res.string.home_sea_creatures,
        icon = Res.drawable.ic_sea_150,
        color = Color(0xFF00BCD4),
        route = "sea_creatures"
    ),
    HomeItem(
        title = Res.string.home_art,
        icon = Res.drawable.ic_artwork_150,
        color = Color(0xFF964B00),
        route = "art"
    ),
    HomeItem(
        title = Res.string.home_fossils,
        icon = Res.drawable.ic_fossil_150,
        color = Color(0xFF795548),
        route = "fossils"
    ),
    HomeItem(
        title = Res.string.home_items,
        icon = Res.drawable.ic_item_150,
        color = Color(0xFFE91E63),
        route = "items"
    ),
    HomeItem(
        title = Res.string.home_events,
        icon = Res.drawable.ic_event_150,
        color = Color(0xFFFFC107),
        route = "events"
    ),
    HomeItem(
        title = Res.string.home_flower_breed,
        icon = Res.drawable.ic_flower_breed_150,
        color = Color(0xFFFF9800),
        route = "flower_breed"
    ),
)
