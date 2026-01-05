package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class ToolNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPaths: List<String> = listOf(
        TOOLS_JSON_PATH,
        ETC_JSON_PATH,
    )
}
