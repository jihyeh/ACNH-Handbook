package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class RecipeNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonFileNames: List<String> = listOf(
        FURNITURE,
        RUGS,
        CAPS,
        HANDBAGS,
        TOPS,
        BOTTOMS,
        DRESS_UP,
        SHOES,
        SOCKS,
        HELMETS,
        ACCESSORIES,
        BAGS,
        UMBRELLAS,
        TOOLS,
        ETC,
        WALLPAPER,
        FLOORS,
        DISHES,
        FENCING,
        DOOR_DECO,
        EVENT_ITEMS,
    )
}
