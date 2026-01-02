package org.jihye.acnhhb.data.repository

import acnhhandbook.composeapp.generated.resources.Res
import io.github.aakira.napier.Napier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jihye.acnhhb.util.AppLocaleManager

class PhotoNameProvider(private val appLocaleManager: AppLocaleManager) {
    private var nameMap: Map<String, PhotoTranslation> = emptyMap()

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun load() {
        if (nameMap.isNotEmpty()) return
        try {
            val allItems = mutableListOf<PhotoTranslation>()
            val filePaths = listOf(
                PHOTOS_JSON_PATH,
                POSTERS_JSON_PATH,
            )

            filePaths.forEach { path ->
                try {
                    val bytes = Res.readBytes(path)
                    val jsonString = bytes.decodeToString()
                    val items = json.decodeFromString<List<PhotoTranslation>>(jsonString)
                    allItems.addAll(items)
                } catch (e: Exception) {
                    println("Failed to load photo translation from $path: ${e.message}")
                }
            }

            nameMap = allItems.associateBy { it.enName?.lowercase() ?: "" }
        } catch (e: Exception) {
            e.printStackTrace()
            nameMap = emptyMap()
        }
    }

    suspend fun getName(name: String): String? {
        if (nameMap.isEmpty()) {
            load()
        }

        val item = nameMap[name.lowercase()] ?: return null.also {
            Napier.e("PhotoNameProvider: $name not found")
        }
        return if (appLocaleManager.isKorean()) {
            item.krName
        } else {
            item.enName
        }
    }

    @Serializable
    private data class PhotoTranslation(
            @SerialName(LOCALE_KEY_ID) val id: String,
            @SerialName(LOCALE_KEY_KOREAN) val krName: String? = null,
            @SerialName(LOCALE_KEY_ENGLISH) val enName: String? = null,
    )

    companion object {
        private const val PHOTOS_JSON_PATH = "files/translate/photos.json"
        private const val POSTERS_JSON_PATH = "files/translate/posters.json"

        private const val LOCALE_KEY_ID = "Id"
        private const val LOCALE_KEY_KOREAN = "KRko"
        private const val LOCALE_KEY_ENGLISH = "USen"
    }
}
