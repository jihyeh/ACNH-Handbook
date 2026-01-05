package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class SeaCreatureNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPaths: List<String> = listOf(SEA_CREATURES_JSON_PATH)
}
