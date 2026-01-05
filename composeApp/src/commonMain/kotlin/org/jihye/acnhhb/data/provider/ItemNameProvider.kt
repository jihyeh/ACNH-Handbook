package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class ItemNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPath: String = "files/translate/item.json"
}
