package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class ItemNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPaths: List<String> = listOf(ITEM_JSON_PATH)
}
