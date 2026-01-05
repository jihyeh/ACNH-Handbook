package org.jihye.acnhhb.data.provider

import acnhhandbook.composeapp.generated.resources.Res
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jihye.acnhhb.data.model.Translation
import org.jihye.acnhhb.util.AppLocaleManager

class ToolNameProvider(private val appLocaleManager: AppLocaleManager) {
    private var nameMap: Map<String, Translation> = emptyMap()

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun load() {
        if (nameMap.isNotEmpty()) return
        try {
            val toolsBytes = Res.readBytes(TOOLS_JSON_PATH)
            val toolsJsonString = toolsBytes.decodeToString()
            val toolsItems = json.decodeFromString<List<Translation>>(toolsJsonString)

            val etcBytes = Res.readBytes(ETC_JSON_PATH)
            val etcJsonString = etcBytes.decodeToString()
            val etcItems = json.decodeFromString<List<Translation>>(etcJsonString)

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

    companion object {
        private const val TOOLS_JSON_PATH = "files/translate/tools.json"
        private const val ETC_JSON_PATH = "files/translate/etc.json"
    }
}
