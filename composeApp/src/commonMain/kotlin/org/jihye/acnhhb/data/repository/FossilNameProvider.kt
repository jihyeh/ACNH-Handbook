package org.jihye.acnhhb.data.repository

import acnhhandbook.composeapp.generated.resources.Res
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jihye.acnhhb.util.AppLocaleManager

class FossilNameProvider(private val appLocaleManager: AppLocaleManager) {
    private var nameMap: Map<String, FossilTranslation> = emptyMap()

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun load() {
        if (nameMap.isNotEmpty()) return
        try {
            val bytes = Res.readBytes(FOSSIL_JSON_PATH)
            val jsonString = bytes.decodeToString()
            val items = json.decodeFromString<List<FossilTranslation>>(jsonString)
            nameMap = items.associateBy { it.enName?.lowercase() ?: "" }
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
    private data class FossilTranslation(
        @SerialName(LOCALE_KEY_ID) val id: String,
        @SerialName(LOCALE_KEY_KOREAN) val krName: String? = null,
        @SerialName(LOCALE_KEY_ENGLISH) val enName: String? = null,
    )

    companion object {
        private const val FOSSIL_JSON_PATH = "files/translate/fossils.json"
        private const val LOCALE_KEY_ID = "Id"
        private const val LOCALE_KEY_KOREAN = "KRko"
        private const val LOCALE_KEY_ENGLISH = "USen"
    }
}
