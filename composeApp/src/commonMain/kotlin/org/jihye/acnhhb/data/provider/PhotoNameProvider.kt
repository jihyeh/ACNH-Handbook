package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class PhotoNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPaths: List<String> = listOf(
        PHOTOS_JSON_PATH,
        POSTERS_JSON_PATH,
    )
}
