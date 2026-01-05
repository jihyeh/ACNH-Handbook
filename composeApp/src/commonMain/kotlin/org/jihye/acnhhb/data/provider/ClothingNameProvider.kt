package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class ClothingNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPaths: List<String> = listOf(
        TOPS_JSON_PATH,
        BOTTOMS_JSON_PATH,
        CAPS_JSON_PATH,
        ACCESSORIES_JSON_PATH,
        UMBRELLAS_JSON_PATH,
        BAGS_JSON_PATH,
        DRESS_UP_JSON_PATH,
        SHOES_JSON_PATH,
        SOCKS_JSON_PATH,
        HANDBAGS_JSON_PATH,
        HELMETS_JSON_PATH,
    )
}
