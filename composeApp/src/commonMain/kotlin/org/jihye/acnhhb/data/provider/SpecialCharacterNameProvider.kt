package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class SpecialCharacterNameProvider(appLocaleManager: AppLocaleManager) : SimpleNameProvider(appLocaleManager) {
    override val jsonFileNames: List<String> = listOf(SPECIAL_CHARACTERS)
}
