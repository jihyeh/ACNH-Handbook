package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class FossilNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPath: String = "files/translate/fossils.json"
}
