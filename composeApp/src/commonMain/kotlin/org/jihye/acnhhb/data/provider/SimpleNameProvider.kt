package org.jihye.acnhhb.data.provider

import acnhhandbook.composeapp.generated.resources.Res
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jihye.acnhhb.data.model.Translation
import org.jihye.acnhhb.util.AppLocaleManager

abstract class SimpleNameProvider(
    protected val appLocaleManager: AppLocaleManager,
) {
    protected val json = Json { ignoreUnknownKeys = true }
    protected var nameMap: Map<String, Translation> = emptyMap()
    private var enNameMap: Map<String, Translation> = emptyMap()

    protected abstract val jsonPath: String

    @OptIn(ExperimentalResourceApi::class)
    open suspend fun load() {
        if (nameMap.isNotEmpty()) return
        try {
            val bytes = Res.readBytes(jsonPath)
            val jsonString = bytes.decodeToString()
            val items = json.decodeFromString<List<Translation>>(jsonString)
            nameMap = items.associateBy { it.enName?.lowercase() ?: "" }
            enNameMap = items.associateBy { it.enName?.lowercase() ?: "" }
        } catch (e: Exception) {
            e.printStackTrace()
            nameMap = emptyMap()
        }
    }

    open suspend fun getName(name: String): String? {
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

    open suspend fun getNameByEnName(enName: String?): String? {
        if (enName == null) return null
        if (enNameMap.isEmpty()) {
            load()
        }

        val item = enNameMap[enName.lowercase()] ?: return null
        return if (appLocaleManager.isKorean()) {
            item.krName
        } else {
            item.enName
        }
    }
}
