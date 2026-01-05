package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class InteriorNameProvider(appLocaleManager: AppLocaleManager) : BaseNameProvider(appLocaleManager) {
    override val jsonFileNames: List<String> = listOf(
        WALLPAPER,
        FLOORS,
        RUGS
    )
}
