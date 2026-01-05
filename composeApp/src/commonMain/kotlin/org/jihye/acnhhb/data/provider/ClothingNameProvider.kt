package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.util.AppLocaleManager

class ClothingNameProvider(appLocaleManager: AppLocaleManager) : BaseNameProvider(appLocaleManager) {
    override val jsonFileNames: List<String> = listOf(
        TOPS,
        BOTTOMS,
        CAPS,
        ACCESSORIES,
        UMBRELLAS,
        BAGS,
        DRESS_UP,
        SHOES,
        SOCKS,
        HANDBAGS,
        HELMETS,
    )
}
