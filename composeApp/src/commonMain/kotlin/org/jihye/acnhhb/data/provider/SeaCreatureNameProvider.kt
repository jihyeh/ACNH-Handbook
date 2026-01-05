package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class SeaCreatureNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonPath: String = "files/translate/sea_creatures.json"
}
