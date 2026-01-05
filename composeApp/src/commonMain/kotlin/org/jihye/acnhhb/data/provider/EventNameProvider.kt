package org.jihye.acnhhb.data.provider

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.suffix_birthday
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString
import org.jihye.acnhhb.data.model.Translation
import org.jihye.acnhhb.util.AppLocaleManager

class EventNameProvider(
    private val appLocaleManager: AppLocaleManager,
    private val villagerNameProvider: VillagerNameProvider,
    private val specialCharacterNameProvider: SpecialCharacterNameProvider,
) {
    private val allNames = mutableMapOf<String, Translation>()

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun load() {
        if (allNames.isNotEmpty()) return
        try {
            val allItems = mutableListOf<Translation>()
            val jsonFileNames = listOf(
                EVENT_NAMES_JSON_PATH,
                EVENT_ITEMS_JSON_PATH,
                ITEM_JSON_PATH,
            )
            jsonFileNames.forEach { fileName ->
                val bytes = Res.readBytes(fileName)
                val jsonString = bytes.decodeToString()
                val items = json.decodeFromString<List<Translation>>(jsonString)
                allItems.addAll(items)
            }
            allNames.putAll(allItems.associateBy { it.enName?.lowercase()?.trim() ?: "" })
        } catch (e: Exception) {
            e.printStackTrace()
            allNames.clear()
        }
    }

    suspend fun getName(name: String): String? {
        if (allNames.isEmpty()) {
            load()
        }

        // 생일 이벤트 처리
        if (name.contains(SUFFIX_S_BIRTHDAY, ignoreCase = true)) {
            val villagerName = name.replace(SUFFIX_S_BIRTHDAY, "", ignoreCase = true).trim()
            val localizedVillager = villagerNameProvider.getNameByEnName(villagerName)
                ?: specialCharacterNameProvider.getNameByEnName(villagerName)
            if (localizedVillager != null && appLocaleManager.isKorean()) {
                return localizedVillager + getString(Res.string.suffix_birthday)
            }
        }

        if (appLocaleManager.isKorean()) {
            // Nook Shopping 이벤트 처리
            if (name.contains(KEYWORD_NOOK_SHOPPING_EVENT, ignoreCase = true)) {
                val suffix =
                    if (name.endsWith(SUFFIX_BEGINS, ignoreCase = true)) SUFFIX_BEGINS
                    else if (name.endsWith(SUFFIX_ENDS, ignoreCase = true)) SUFFIX_ENDS
                    else null

                if (suffix != null) {
                    val localizedSuffix =
                        if (suffix.equals(SUFFIX_BEGINS, true)) SUFFIX_BEGINS_KR
                        else SUFFIX_ENDS_KR
                    val baseName =
                        name.replace(KEYWORD_NOOK_SHOPPING_EVENT, "", ignoreCase = true)
                            .replace(suffix, "", ignoreCase = true)
                            .trim()

                    val localizedBaseName = allNames[baseName.lowercase()]?.krName ?: baseName

                    return "$localizedBaseName $KEYWORD_NOOK_SHOPPING_EVENT $localizedSuffix"
                }
            }

            // 레시피 획득 마지막 날 처리
            if (name.startsWith(PREFIX_LAST_DAY, ignoreCase = true) &&
                name.endsWith(SUFFIX_RECIPES_ARE_AVAILABLE, ignoreCase = true)
            ) {
                val rawName = name.removePrefix(PREFIX_LAST_DAY)
                    .replace(SUFFIX_RECIPES_ARE_AVAILABLE, "", ignoreCase = true)
                    .trim()

                val localizedName = allNames[rawName.lowercase()]?.krName
                val prefix = localizedName ?: rawName
                return "$prefix $SUFFIX_RECIPES_LAST_DAY_KR"
            }

            // 일반적인 접미사 (시작, 종료, 레시피 획득 등) 처리
            val replaceRules =
                listOf(
                    SUFFIX_RECIPES_AVAILABLE to SUFFIX_RECIPES_START_KR,
                    SUFFIX_RECIPES_ARE_AVAILABLE to SUFFIX_RECIPES_AVAILABLE_KR,
                    SUFFIX_BEGINS to SUFFIX_BEGINS_KR,
                    SUFFIX_BEGIN to SUFFIX_BEGINS_KR,
                    SUFFIX_ENDS to SUFFIX_ENDS_KR,
                    SUFFIX_END to SUFFIX_ENDS_KR,
                )
            for ((suffix, replacement) in replaceRules) {
                if (name.contains(suffix, ignoreCase = true)) {
                    val rawName = name.replace(suffix, "", ignoreCase = true).trim()
                    val localizedName = allNames[rawName.lowercase()]?.krName
                    val prefix = localizedName ?: rawName
                    return "$prefix $replacement"
                }
            }
        }

        // 직접 번역된 이름 찾기 (마지막 수단)
        val item = allNames[name.lowercase().trim()] ?: return null
        return if (appLocaleManager.isKorean()) {
            item.krName
        } else {
            item.enName
        }
    }

    companion object {
        private const val EVENT_NAMES_JSON_PATH = "files/translate/event_names.json"
        private const val EVENT_ITEMS_JSON_PATH = "files/translate/event_items.json"
        private const val ITEM_JSON_PATH = "files/translate/item.json"

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
