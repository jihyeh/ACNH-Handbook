package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class FossilNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPaths: List<String> = listOf(FOSSILS_JSON_PATH)
}
