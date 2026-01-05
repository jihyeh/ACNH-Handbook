package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class PhotoNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonFileNames: List<String> = listOf(
        PHOTOS,
        POSTERS,
    )
}
