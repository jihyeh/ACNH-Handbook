package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class FurnitureNameProvider(appLocaleManager: AppLocaleManager) : BaseNameProvider(appLocaleManager) {
    override val jsonFileNames: List<String> = listOf(
        FURNITURE,
        DISHES,
        BUG_MODELS,
        FISH_MODELS,
        DOOR_DECO,
        ART,
    )
}
