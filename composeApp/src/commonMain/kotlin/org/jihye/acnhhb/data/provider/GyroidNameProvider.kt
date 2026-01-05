package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class GyroidNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPaths: List<String> = listOf(GYROIDS_JSON_PATH)
}
