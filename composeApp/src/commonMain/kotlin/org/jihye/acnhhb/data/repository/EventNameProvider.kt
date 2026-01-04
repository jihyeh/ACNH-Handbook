package org.jihye.acnhhb.data.repository

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.suffix_birthday
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString
import org.jihye.acnhhb.util.AppLocaleManager

class EventNameProvider(
    private val appLocaleManager: AppLocaleManager,
    private val villagerNameProvider: VillagerNameProvider,
) {
    private var nameMap: Map<String, EventTranslation> = emptyMap()
    private var eventItemsMap: Map<String, EventTranslation> = emptyMap()
    private var itemMap: Map<String, EventTranslation> = emptyMap()
    private var specialCharactersMap: Map<String, EventTranslation> = emptyMap()

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun load() {
        if (nameMap.isNotEmpty()) return
        try {
            val bytes = Res.readBytes(EVENT_NAMES_JSON_PATH)
            val jsonString = bytes.decodeToString()
            val items = json.decodeFromString<List<EventTranslation>>(jsonString)
            nameMap = items.associateBy { it.enName?.lowercase()?.trim() ?: "" }

            val itemBytes = Res.readBytes(EVENT_ITEMS_JSON_PATH)
            val itemJsonString = itemBytes.decodeToString()
            val eventItems = json.decodeFromString<List<EventTranslation>>(itemJsonString)
            eventItemsMap = eventItems.associateBy { it.enName?.lowercase()?.trim() ?: "" }

            val itemMapBytes = Res.readBytes(ITEM_JSON_PATH)
            val itemMapJsonString = itemMapBytes.decodeToString()
            val itemMapItems = json.decodeFromString<List<EventTranslation>>(itemMapJsonString)
            itemMap = itemMapItems.associateBy { it.enName?.lowercase()?.trim() ?: "" }

            val specialCharactersBytes = Res.readBytes(SPECIAL_CHARACTERS_JSON_PATH)
            val specialCharactersJsonString = specialCharactersBytes.decodeToString()
            val specialCharactersItems =
                json.decodeFromString<List<EventTranslation>>(specialCharactersJsonString)
            specialCharactersMap =
                specialCharactersItems.associateBy { it.enName?.lowercase()?.trim() ?: "" }
        } catch (e: Exception) {
            e.printStackTrace()
            // Keep what we loaded
            if (nameMap.isEmpty()) nameMap = emptyMap()
            if (eventItemsMap.isEmpty()) eventItemsMap = emptyMap()
            if (itemMap.isEmpty()) itemMap = emptyMap()
            if (specialCharactersMap.isEmpty()) specialCharactersMap = emptyMap()
        }
    }

    suspend fun getName(name: String, type: String? = null): String? {
        if (nameMap.isEmpty()) {
            load()
        }

        // 생일 이벤트 처리
        if (type == EVENT_TYPE_BIRTHDAY || name.contains(KEYWORD_BIRTHDAY)) {
            val villagerName = name.replace(SUFFIX_S_BIRTHDAY, "", ignoreCase = true).trim()
            val localizedVillager =
                villagerNameProvider.getNameByEnName(villagerName)
                    ?: specialCharactersMap[villagerName.lowercase()]?.krName
                    ?: specialCharactersMap.values.find {
                        it.enName?.contains(villagerName, ignoreCase = true) == true
                    }?.krName

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

                    val localizedBaseName =
                        eventItemsMap[baseName.lowercase()]?.krName
                            ?: nameMap[baseName.lowercase()]?.krName
                            ?: itemMap[baseName.lowercase()]?.krName ?: baseName

                    return "$localizedBaseName $KEYWORD_NOOK_SHOPPING_EVENT $localizedSuffix"
                }
            }

            // 레시피 획득 마지막 날 처리
            if (name.startsWith(PREFIX_LAST_DAY, ignoreCase = true) &&
                name.endsWith(SUFFIX_RECIPES_ARE_AVAILABLE, ignoreCase = true)
            ) {
                val rawName =
                    name.removePrefix(PREFIX_LAST_DAY)
                        .replace(SUFFIX_RECIPES_ARE_AVAILABLE, "", ignoreCase = true)
                        .trim()

                val localizedName =
                    eventItemsMap[rawName.lowercase()]?.krName
                        ?: nameMap[rawName.lowercase()]?.krName
                        ?: itemMap[rawName.lowercase()]?.krName
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
                    val localizedName =
                        eventItemsMap[rawName.lowercase()]?.krName
                            ?: nameMap[rawName.lowercase()]?.krName
                            ?: itemMap[rawName.lowercase()]?.krName
                    val prefix = localizedName ?: rawName
                    return "$prefix $replacement"
                }
            }
        }

        // 직접 번역된 이름 찾기 (마지막 수단)
        val item = nameMap[name.lowercase().trim()] ?: return null
        return if (appLocaleManager.isKorean()) {
            item.krName
        } else {
            item.enName
        }
    }

    @Serializable
    private data class EventTranslation(
        @SerialName(LOCALE_KEY_ID) val id: String,
        @SerialName(LOCALE_KEY_KOREAN) val krName: String? = null,
        @SerialName(LOCALE_KEY_ENGLISH) val enName: String? = null,
    )

    companion object {
        private const val EVENT_NAMES_JSON_PATH = "files/translate/event_names.json"
        private const val EVENT_ITEMS_JSON_PATH = "files/translate/event_items.json"
        private const val ITEM_JSON_PATH = "files/translate/item.json"
        private const val SPECIAL_CHARACTERS_JSON_PATH = "files/translate/special_characters.json"

        private const val LOCALE_KEY_ID = "Id"
        private const val LOCALE_KEY_KOREAN = "KRko"
        private const val LOCALE_KEY_ENGLISH = "USen"

        private const val EVENT_TYPE_BIRTHDAY = "Birthday"
        private const val KEYWORD_BIRTHDAY = "Birthday"
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
