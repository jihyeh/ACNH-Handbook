package org.jihye.acnhhb.data.provider

import acnhhandbook.composeapp.generated.resources.Res
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jihye.acnhhb.data.model.Translation
import org.jihye.acnhhb.util.AppLocaleManager

class FurnitureNameProvider(private val appLocaleManager: AppLocaleManager) {
    private var nameMap: Map<String, Translation> = emptyMap()

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun load() {
        if (nameMap.isNotEmpty()) return
        try {
            val allItems = mutableListOf<Translation>()
            val filePaths =
                listOf(
                    FURNITURE_JSON_PATH,
                    DISHES_JSON_PATH,
                    BUG_MODELS_JSON_PATH,
                    FISH_MODELS_JSON_PATH,
                    DOOR_DECO_JSON_PATH,
                    ART_JSON_PATH,
                )

            filePaths.forEach { path ->
                try {
                    val bytes = Res.readBytes(path)
                    val jsonString = bytes.decodeToString()
                    val items = json.decodeFromString<List<Translation>>(jsonString)
                    allItems.addAll(items)
                } catch (e: Exception) {
                    println("Failed to load furniture translation from $path: ${e.message}")
                }
            }

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

        val lowerName = name.lowercase()
        // Check for "(fake)" suffix logic
        if (lowerName.endsWith(FAKE_SUFFIX_EN)) {
            val baseName = lowerName.removeSuffix(FAKE_SUFFIX_EN)
            val item = nameMap[baseName]
            return if (item != null) {
                if (appLocaleManager.isKorean()) {
                    "${item.krName}$FAKE_SUFFIX_KR"
                } else {
                    "${item.enName}$FAKE_SUFFIX_EN"
                }
            } else {
                // If base name not found, try raw lookup (unlikely but safe fallback)
                // or just return null to let original name be used
                null
            }
        }

        val item = nameMap[lowerName] ?: return null
        return if (appLocaleManager.isKorean()) {
            item.krName
        } else {
            item.enName
        }
    }

    companion object {
        private const val FURNITURE_JSON_PATH = "files/translate/furniture.json"
        private const val DISHES_JSON_PATH = "files/translate/dishes.json"
        private const val BUG_MODELS_JSON_PATH = "files/translate/bug_models.json"
        private const val FISH_MODELS_JSON_PATH = "files/translate/fish_models.json"
        private const val DOOR_DECO_JSON_PATH = "files/translate/door deco.json"
        private const val ART_JSON_PATH = "files/translate/art.json"

        private const val FAKE_SUFFIX_EN = "(fake)"
        private const val FAKE_SUFFIX_KR = "(가품)"
    }
}
