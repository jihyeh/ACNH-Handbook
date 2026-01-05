package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class BugNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPaths: List<String> = listOf(BUG_JSON_PATH)
}
