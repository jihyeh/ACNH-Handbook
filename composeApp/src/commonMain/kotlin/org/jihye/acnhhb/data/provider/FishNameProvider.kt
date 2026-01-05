package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class FishNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonFileNames: List<String> = listOf(FISH)
}
