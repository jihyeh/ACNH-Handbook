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

    protected abstract val jsonFileNames: List<String>

    @OptIn(ExperimentalResourceApi::class)
    open suspend fun load() {
        if (nameMap.isNotEmpty()) return
        try {
            val allItems = mutableListOf<Translation>()
            jsonFileNames.forEach { fileName ->
                val bytes = Res.readBytes(JSON_PATH + fileName)
                val jsonString = bytes.decodeToString()
                val items = json.decodeFromString<List<Translation>>(jsonString)
                allItems.addAll(items)
            }
            nameMap = allItems.associateBy { it.enName?.lowercase() ?: "" }
            enNameMap = allItems.associateBy { it.enName?.lowercase() ?: "" }
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

    companion object {
        protected const val JSON_PATH = "files/translate/"
        protected const val FURNITURE = "furniture.json"
        protected const val RUGS = "rugs.json"
        protected const val CAPS = "caps.json"
        protected const val HANDBAGS = "handbags.json"
        protected const val TOPS = "tops.json"
        protected const val BOTTOMS = "bottoms.json"
        protected const val DRESS_UP = "dress-up.json"
        protected const val SHOES = "shoes.json"
        protected const val SOCKS = "socks.json"
        protected const val HELMETS = "helmets.json"
        protected const val ACCESSORIES = "accessories.json"
        protected const val BAGS = "bags.json"
        protected const val UMBRELLAS = "umbrellas.json"
        protected const val TOOLS = "tools.json"
        protected const val ETC = "etc.json"
        protected const val WALLPAPER = "wallpaper.json"
        protected const val FLOORS = "floors.json"
        protected const val DISHES = "dishes.json"
        protected const val FENCING = "fencing.json"
        protected const val DOOR_DECO = "door deco.json"
        protected const val EVENT_ITEMS = "event_items.json"
        protected const val ART = "art.json"
        protected const val BUG = "bugs.json"
        protected const val FISH = "fish.json"
        protected const val FOSSILS = "fossils.json"
        protected const val GYROIDS = "gyroids.json"
        protected const val ITEM = "item.json"
        protected const val PHOTOS = "photos.json"
        protected const val POSTERS = "posters.json"
        protected const val SEA_CREATURES = "sea_creatures.json"
        protected const val VILLAGER = "villagers.json"
    }
}
