package org.jihye.acnhhb.data.provider

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.suffix_birthday
import org.jetbrains.compose.resources.getString
import org.jihye.acnhhb.util.AppLocaleManager

class EventNameProvider(
    appLocaleManager: AppLocaleManager,
    private val villagerNameProvider: VillagerNameProvider,
    private val specialCharacterNameProvider: SpecialCharacterNameProvider,
) : SimpleNameProvider(appLocaleManager) {

    override val jsonFileNames: List<String> = listOf(
        EVENT_NAMES,
        EVENT_ITEMS,
        ITEM,
    )

    override suspend fun getName(name: String): String? {
        if (nameMap.isEmpty()) {
            load()
        }
        val isKorean = appLocaleManager.isKorean()

        // 생일 이벤트 처리
        if (name.contains(SUFFIX_S_BIRTHDAY, ignoreCase = true)) {
            val villagerName = name.replace(SUFFIX_S_BIRTHDAY, "", ignoreCase = true).trim()
            val localizedVillager = villagerNameProvider.getNameByEnName(villagerName)
                ?: specialCharacterNameProvider.getNameByEnName(villagerName)
            if (localizedVillager != null && isKorean) {
                return localizedVillager + getString(Res.string.suffix_birthday)
            }
        }

        if (!isKorean) return super.getName(name)

        // 2. Handle Specific Event Patterns (Nook Shopping / Last Day)
        val result = when {
            // Recipe Last Day
            name.startsWith(PREFIX_LAST_DAY, ignoreCase = true) &&
                name.endsWith(SUFFIX_RECIPES_ARE_AVAILABLE, ignoreCase = true) -> {
                val raw = name.removePrefix(PREFIX_LAST_DAY)
                    .removeSuffixIgnoringCase(SUFFIX_RECIPES_ARE_AVAILABLE).trim()
                val localized = nameMap[raw.lowercase()]?.krName ?: raw
                "$localized $SUFFIX_RECIPES_LAST_DAY_KR"
            }

            // Nook Shopping
            name.contains(KEYWORD_NOOK_SHOPPING_EVENT, ignoreCase = true) -> {
                parseNookShopping(name)
            }

            // 3. General Suffixes (Dynamic replacement)
            else -> parseGeneralSuffix(name)
        }

        return result ?: super.getName(name)
    }

    private fun parseNookShopping(name: String): String? {
        val suffix = when {
            name.endsWith(SUFFIX_BEGINS, true) -> SUFFIX_BEGINS
            name.endsWith(SUFFIX_ENDS, true) -> SUFFIX_ENDS
            else -> return null
        }

        val localizedSuffix = if (suffix == SUFFIX_BEGINS) SUFFIX_BEGINS_KR else SUFFIX_ENDS_KR
        val baseName = name
            .replace(KEYWORD_NOOK_SHOPPING_EVENT, "", ignoreCase = true)
            .replace(suffix, "", ignoreCase = true).trim()

        val localizedBase = nameMap[baseName.lowercase()]?.krName ?: baseName
        return "$localizedBase $KEYWORD_NOOK_SHOPPING_EVENT $localizedSuffix"
    }

    private fun parseGeneralSuffix(name: String): String? {
        // Order from longest to shortest to prevent partial matches
        val rules = listOf(
            SUFFIX_RECIPES_ARE_AVAILABLE to SUFFIX_RECIPES_AVAILABLE_KR,
            SUFFIX_RECIPES_AVAILABLE to SUFFIX_RECIPES_START_KR,
            SUFFIX_BEGINS to SUFFIX_BEGINS_KR,
            SUFFIX_BEGIN to SUFFIX_BEGINS_KR,
            SUFFIX_ENDS to SUFFIX_ENDS_KR,
            SUFFIX_END to SUFFIX_ENDS_KR
        )

        for ((enSuffix, krSuffix) in rules) {
            if (name.endsWith(enSuffix, ignoreCase = true)) {
                val rawName = name.removeSuffixIgnoringCase(enSuffix).trim()
                val localizedName = nameMap[rawName.lowercase()]?.krName ?: rawName
                return "$localizedName $krSuffix"
            }
        }
        return null
    }

    // Helper for cleaner code
    fun String.removeSuffixIgnoringCase(suffix: String): String {
        return if (endsWith(suffix, ignoreCase = true)) substring(0, length - suffix.length) else this
    }

    companion object {
        private const val SUFFIX_S_BIRTHDAY = "'s Birthday"

        private const val SUFFIX_BEGINS = "begins"
        private const val SUFFIX_BEGIN = "begin"
        private const val SUFFIX_BEGINS_KR = "시작"
        private const val SUFFIX_ENDS = "ends"
        private const val SUFFIX_END = "end"
        private const val SUFFIX_ENDS_KR = "종료"

        private const val SUFFIX_RECIPES_AVAILABLE = "recipes become available"
        private const val SUFFIX_RECIPES_ARE_AVAILABLE = "recipes are available"
        private const val SUFFIX_RECIPES_AVAILABLE_KR = "레시피 획득 가능"
        private const val SUFFIX_RECIPES_START_KR = "레시피 획득 시작"

        private const val PREFIX_LAST_DAY = "Last day"
        private const val SUFFIX_RECIPES_LAST_DAY_KR = "레시피 획득 마지막 날"

        private const val KEYWORD_NOOK_SHOPPING_EVENT = "Nook Shopping event"
    }
}
