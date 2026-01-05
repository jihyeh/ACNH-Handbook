package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class FishNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPaths: List<String> = listOf(FISH_JSON_PATH)
}
