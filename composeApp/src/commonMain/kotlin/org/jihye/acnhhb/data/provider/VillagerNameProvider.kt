package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class VillagerNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPaths: List<String> = listOf(VILLAGER_JSON_PATH)
}
