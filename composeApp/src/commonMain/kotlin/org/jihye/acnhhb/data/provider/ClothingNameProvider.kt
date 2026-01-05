package org.jihye.acnhhb.data.provider

import acnhhandbook.composeapp.generated.resources.Res
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jihye.acnhhb.data.model.Translation
import org.jihye.acnhhb.util.AppLocaleManager

class ClothingNameProvider(private val appLocaleManager: AppLocaleManager) {
    private var nameMap: Map<String, Translation> = emptyMap()

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun load() {
        if (nameMap.isNotEmpty()) return
        try {
            val allItems = mutableListOf<Translation>()

            val filePaths = listOf(
                TOPS_JSON_PATH,
                BOTTOMS_JSON_PATH,
                CAPS_JSON_PATH,
                ACCESSORIES_JSON_PATH,
                UMBRELLAS_JSON_PATH,
                BAGS_JSON_PATH,
                DRESS_UP_JSON_PATH,
                SHOES_JSON_PATH,
                SOCKS_JSON_PATH,
                HANDBAGS_JSON_PATH,
                HELMETS_JSON_PATH,
            )

            filePaths.forEach { path ->
                try {
                    val bytes = Res.readBytes(path)
                    val jsonString = bytes.decodeToString()
                    val items = json.decodeFromString<List<Translation>>(jsonString)
                    allItems.addAll(items)
                } catch (e: Exception) {
                    println("Failed to load clothing translation from $path: ${e.message}")
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

        val item = nameMap[name.lowercase()] ?: return null
        return if (appLocaleManager.isKorean()) {
            item.krName
        } else {
            item.enName
        }
    }

    companion object {
        private const val TOPS_JSON_PATH = "files/translate/tops.json"
        private const val BOTTOMS_JSON_PATH = "files/translate/bottoms.json"
        private const val CAPS_JSON_PATH = "files/translate/caps.json"
        private const val ACCESSORIES_JSON_PATH = "files/translate/accessories.json"
        private const val UMBRELLAS_JSON_PATH = "files/translate/umbrellas.json"
        private const val BAGS_JSON_PATH = "files/translate/bags.json"
        private const val DRESS_UP_JSON_PATH = "files/translate/dress-up.json"
        private const val SHOES_JSON_PATH = "files/translate/shoes.json"
        private const val SOCKS_JSON_PATH = "files/translate/socks.json"
        private const val HANDBAGS_JSON_PATH = "files/translate/handbags.json"
        private const val HELMETS_JSON_PATH = "files/translate/helmets.json"
    }
}
