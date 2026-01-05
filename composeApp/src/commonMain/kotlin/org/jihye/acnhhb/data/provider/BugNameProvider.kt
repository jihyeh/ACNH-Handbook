package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class BugNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPath: String = "files/translate/bugs.json"
}
