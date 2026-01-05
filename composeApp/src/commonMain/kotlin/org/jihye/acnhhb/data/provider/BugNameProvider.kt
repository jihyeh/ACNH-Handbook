package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class BugNameProvider(appLocaleManager: AppLocaleManager) : BaseNameProvider(appLocaleManager) {
    override val jsonFileNames: List<String> = listOf(BUG)
}
