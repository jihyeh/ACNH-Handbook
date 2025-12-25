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

enum class Category(
    val title: StringResource,
    val icon: DrawableResource,
    val color: Color,
) {
    VILLAGERS(
        title = Res.string.home_villagers,
        icon = Res.drawable.ic_villager_150,
        color = Color(0xFF4CAF50),
    ),
    BUGS(
        title = Res.string.home_bugs,
        icon = Res.drawable.ic_bug_150,
        color = Color(0xFF8BC34A),
    ),
    FISH(
        title = Res.string.home_fish,
        icon = Res.drawable.ic_fish_150,
        color = Color(0xFF2196F3),
    ),
    SEA_CREATURES(
        title = Res.string.home_sea_creatures,
        icon = Res.drawable.ic_sea_150,
        color = Color(0xFF00BCD4),
    ),
    ART(
        title = Res.string.home_art,
        icon = Res.drawable.ic_artwork_150,
        color = Color(0xFF964B00),
    ),
    FOSSILS(
        title = Res.string.home_fossils,
        icon = Res.drawable.ic_fossil_150,
        color = Color(0xFF795548),
    ),
    ITEMS(
        title = Res.string.home_items,
        icon = Res.drawable.ic_item_150,
        color = Color(0xFFE91E63),
    ),
    EVENTS(
        title = Res.string.home_events,
        icon = Res.drawable.ic_event_150,
        color = Color(0xFFFFC107),
    ),
    FLOWER_BREED(
        title = Res.string.home_flower_breed,
        icon = Res.drawable.ic_flower_breed_150,
        color = Color(0xFFFF9800),
    ),
}
