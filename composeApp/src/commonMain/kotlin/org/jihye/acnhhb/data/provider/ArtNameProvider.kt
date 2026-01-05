package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class ArtNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPaths: List<String> = listOf(ART_JSON_PATH)
}
