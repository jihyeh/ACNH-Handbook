package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class RecipeNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPaths: List<String> = listOf(
        FURNITURE_JSON_PATH,
        RUGS_JSON_PATH,
        CAPS_JSON_PATH,
        HANDBAGS_JSON_PATH,
        TOPS_JSON_PATH,
        BOTTOMS_JSON_PATH,
        DRESS_UP_JSON_PATH,
        SHOES_JSON_PATH,
        SOCKS_JSON_PATH,
        HELMETS_JSON_PATH,
        ACCESSORIES_JSON_PATH,
        BAGS_JSON_PATH,
        UMBRELLAS_JSON_PATH,
        TOOLS_JSON_PATH,
        ETC_JSON_PATH,
        WALLPAPER_JSON_PATH,
        FLOORS_JSON_PATH,
        DISHES_JSON_PATH,
        FENCING_JSON_PATH,
        DOOR_DECO_JSON_PATH,
        EVENT_ITEMS_JSON_PATH,
    )
}
