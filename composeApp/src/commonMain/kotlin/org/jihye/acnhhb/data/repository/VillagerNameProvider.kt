package org.jihye.acnhhb.data.repository

import acnhhandbook.composeapp.generated.resources.Res
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jihye.acnhhb.util.AppLocaleManager

class VillagerNameProvider(private val appLocaleManager: AppLocaleManager) {
    private var nameMap: Map<String, VillagerTranslation> = emptyMap()

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun load() {
        if (nameMap.isNotEmpty()) return
        try {
            val bytes = Res.readBytes(VILLAGERS_JSON_PATH)
            val jsonString = bytes.decodeToString()
            val items = json.decodeFromString<List<VillagerTranslation>>(jsonString)
            nameMap = items.associateBy { it.id }
        } catch (e: Exception) {
            e.printStackTrace()
            nameMap = emptyMap()
        }
    }

    suspend fun getName(id: String?): String? {
        if (id == null) return null
        if (nameMap.isEmpty()) {
            load()
        }

        val item = nameMap[id] ?: return null
        return if (appLocaleManager.isKorean()) {
            item.krName
        } else {
            item.enName
        }
    }

    @Serializable
    private data class VillagerTranslation(
        @SerialName(LOCALE_KEY_ID) val id: String,
        @SerialName(LOCALE_KEY_KOREAN) val krName: String? = null,
        @SerialName(LOCALE_KEY_ENGLISH) val enName: String? = null,
    )

    companion object {
        private const val VILLAGERS_JSON_PATH = "files/translate/villagers.json"
        private const val LOCALE_KEY_ID = "Id"
        private const val LOCALE_KEY_KOREAN = "KRko"
        private const val LOCALE_KEY_ENGLISH = "USen"
    }
}