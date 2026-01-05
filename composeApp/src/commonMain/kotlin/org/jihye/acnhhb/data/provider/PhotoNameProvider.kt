package org.jihye.acnhhb.data.provider

import acnhhandbook.composeapp.generated.resources.Res
import io.github.aakira.napier.Napier
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jihye.acnhhb.data.model.Translation
import org.jihye.acnhhb.util.AppLocaleManager

class PhotoNameProvider(private val appLocaleManager: AppLocaleManager) {
    private var nameMap: Map<String, Translation> = emptyMap()

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun load() {
        if (nameMap.isNotEmpty()) return
        try {
            val allItems = mutableListOf<Translation>()
            val filePaths = listOf(
                PHOTOS_JSON_PATH,
                POSTERS_JSON_PATH,
            )

            filePaths.forEach { path ->
                try {
                    val bytes = Res.readBytes(path)
                    val jsonString = bytes.decodeToString()
                    val items = json.decodeFromString<List<Translation>>(jsonString)
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

    companion object {
        private const val PHOTOS_JSON_PATH = "files/translate/photos.json"
        private const val POSTERS_JSON_PATH = "files/translate/posters.json"
    }
}
