package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class InteriorNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPaths: List<String> = listOf(
        WALLPAPER_JSON_PATH,
        FLOORS_JSON_PATH,
        RUGS_JSON_PATH
    )
}
