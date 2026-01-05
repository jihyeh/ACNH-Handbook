package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class GyroidNameProvider(appLocaleManager: AppLocaleManager) : BaseNameProvider(appLocaleManager) {
    override val jsonFileNames: List<String> = listOf(GYROIDS)
}
