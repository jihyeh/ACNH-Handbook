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

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun load() {
        if (nameMap.isNotEmpty()) return
        try {
            val bytes = Res.readBytes(EVENT_NAMES_JSON_PATH)
            val jsonString = bytes.decodeToString()
            val items = json.decodeFromString<List<EventTranslation>>(jsonString)
            nameMap = items.associateBy { it.enName?.lowercase() ?: "" }
        } catch (e: Exception) {
            e.printStackTrace()
            nameMap = emptyMap()
        }
    }

    suspend fun getName(name: String, type: String? = null): String? {
        if (nameMap.isEmpty()) {
            load()
        }

        if (type == EVENT_TYPE_BIRTHDAY || name.contains(KEYWORD_BIRTHDAY)) {
            val villagerName = name.replace(SUFFIX_S_BIRTHDAY, "", ignoreCase = true).trim()
            val localizedVillager = villagerNameProvider.getNameByEnName(villagerName)
            if (localizedVillager != null && appLocaleManager.isKorean()) {
                return localizedVillager + getString(Res.string.suffix_birthday)
            }
        }

        val item = nameMap[name.lowercase()] ?: return null
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

        private const val LOCALE_KEY_ID = "Id"
        private const val LOCALE_KEY_KOREAN = "KRko"
        private const val LOCALE_KEY_ENGLISH = "USen"

        private const val EVENT_TYPE_BIRTHDAY = "Birthday"
        private const val KEYWORD_BIRTHDAY = "Birthday"
        private const val SUFFIX_S_BIRTHDAY = "'s Birthday"
    }
}
