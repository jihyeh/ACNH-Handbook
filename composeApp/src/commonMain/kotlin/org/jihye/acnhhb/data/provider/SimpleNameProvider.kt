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

    protected abstract val jsonPaths: List<String>

    @OptIn(ExperimentalResourceApi::class)
    open suspend fun load() {
        if (nameMap.isNotEmpty()) return
        try {
            val allItems = mutableListOf<Translation>()
            jsonPaths.forEach { path ->
                val bytes = Res.readBytes(path)
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
        protected const val FURNITURE_JSON_PATH = "files/translate/furniture.json"
        protected const val RUGS_JSON_PATH = "files/translate/rugs.json"
        protected const val CAPS_JSON_PATH = "files/translate/caps.json"
        protected const val HANDBAGS_JSON_PATH = "files/translate/handbags.json"
        protected const val TOPS_JSON_PATH = "files/translate/tops.json"
        protected const val BOTTOMS_JSON_PATH = "files/translate/bottoms.json"
        protected const val DRESS_UP_JSON_PATH = "files/translate/dress-up.json"
        protected const val SHOES_JSON_PATH = "files/translate/shoes.json"
        protected const val SOCKS_JSON_PATH = "files/translate/socks.json"
        protected const val HELMETS_JSON_PATH = "files/translate/helmets.json"
        protected const val ACCESSORIES_JSON_PATH = "files/translate/accessories.json"
        protected const val BAGS_JSON_PATH = "files/translate/bags.json"
        protected const val UMBRELLAS_JSON_PATH = "files/translate/umbrellas.json"
        protected const val TOOLS_JSON_PATH = "files/translate/tools.json"
        protected const val ETC_JSON_PATH = "files/translate/etc.json"
        protected const val WALLPAPER_JSON_PATH = "files/translate/wallpaper.json"
        protected const val FLOORS_JSON_PATH = "files/translate/floors.json"
        protected const val DISHES_JSON_PATH = "files/translate/dishes.json"
        protected const val FENCING_JSON_PATH = "files/translate/fencing.json"
        protected const val DOOR_DECO_JSON_PATH = "files/translate/door deco.json"
        protected const val EVENT_ITEMS_JSON_PATH = "files/translate/event_items.json"
        protected const val ART_JSON_PATH = "files/translate/art.json"
        protected const val BUG_JSON_PATH = "files/translate/bugs.json"
        protected const val FISH_JSON_PATH = "files/translate/fish.json"
        protected const val FOSSILS_JSON_PATH = "files/translate/fossils.json"
        protected const val GYROIDS_JSON_PATH = "files/translate/gyroids.json"
        protected const val ITEM_JSON_PATH = "files/translate/item.json"
        protected const val PHOTOS_JSON_PATH = "files/translate/photos.json"
        protected const val POSTERS_JSON_PATH = "files/translate/posters.json"
        protected const val SEA_CREATURES_JSON_PATH = "files/translate/sea_creatures.json"
        protected const val VILLAGER_JSON_PATH = "files/translate/villagers.json"
    }
}
