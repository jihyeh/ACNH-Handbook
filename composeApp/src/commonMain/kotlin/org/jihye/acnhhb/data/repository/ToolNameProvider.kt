package org.jihye.acnhhb.data.repository

import acnhhandbook.composeapp.generated.resources.Res
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jihye.acnhhb.util.AppLocaleManager

class ToolNameProvider(private val appLocaleManager: AppLocaleManager) {
    private var nameMap: Map<String, ToolTranslation> = emptyMap()

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun load() {
        if (nameMap.isNotEmpty()) return
        try {
            val toolsBytes = Res.readBytes(TOOLS_JSON_PATH)
            val toolsJsonString = toolsBytes.decodeToString()
            val toolsItems = json.decodeFromString<List<ToolTranslation>>(toolsJsonString)

            val etcBytes = Res.readBytes(ETC_JSON_PATH)
            val etcJsonString = etcBytes.decodeToString()
            val etcItems = json.decodeFromString<List<ToolTranslation>>(etcJsonString)

            val allItems = toolsItems + etcItems
            nameMap = allItems.associateBy { it.enName?.lowercase() ?: "" }
        } catch (e: Exception) {
            e.printStackTrace()
            // Fallback or empty map on error
            nameMap = emptyMap()
        }
    }

    suspend fun getName(name: String): String? {
        if (nameMap.isEmpty()) {
            load()
        }

        val item = nameMap[name.lowercase()] ?: return null
        return if (appLocaleManager.isKorean()) {
            item.krName
        } else {
            item.enName
        }
    }

    @Serializable
    private data class ToolTranslation(
        @SerialName(LOCALE_KEY_ID) val id: String,
        @SerialName(LOCALE_KEY_KOREAN) val krName: String? = null,
        @SerialName(LOCALE_KEY_ENGLISH) val enName: String? = null,
    )

    companion object {
        private const val TOOLS_JSON_PATH = "files/translate/tools.json"
        private const val ETC_JSON_PATH = "files/translate/etc.json"
        private const val LOCALE_KEY_ID = "Id"
        private const val LOCALE_KEY_KOREAN = "KRko"
        private const val LOCALE_KEY_ENGLISH = "USen"
    }
}
