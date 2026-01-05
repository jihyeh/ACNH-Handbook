package org.jihye.acnhhb.data.provider

import org.jihye.acnhhb.data.model.Translation
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

    override suspend fun getName(name: String): String? {
        if (nameMap.isEmpty()) {
            load()
        }

        val lowerName = name.lowercase()

        // Handle the "Fake" suffix logic
        if (lowerName.endsWith(FAKE_SUFFIX_EN)) {
            val baseName = lowerName.removeSuffix(FAKE_SUFFIX_EN).trim()
            val item = nameMap[baseName] ?: return null

            return formatFakeName(item)
        }

        return super.getName(name)
    }

    private fun formatFakeName(item: Translation): String {
        return if (appLocaleManager.isKorean()) {
            "${item.krName} $FAKE_SUFFIX_KR"
        } else {
            "${item.enName} $FAKE_SUFFIX_EN"
        }
    }

    companion object {
        private const val FAKE_SUFFIX_EN = "(fake)"
        private const val FAKE_SUFFIX_KR = "(가품)"
    }
}
