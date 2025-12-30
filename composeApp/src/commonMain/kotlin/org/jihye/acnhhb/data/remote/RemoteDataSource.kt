package org.jihye.acnhhb.data.remote

import org.jihye.acnhhb.data.remote.dto.ArtResponse
import org.jihye.acnhhb.data.remote.dto.BugResponse
import org.jihye.acnhhb.data.remote.dto.FishResponse
import org.jihye.acnhhb.data.remote.dto.FossilResponse
import org.jihye.acnhhb.data.remote.dto.GyroidResponse
import org.jihye.acnhhb.data.remote.dto.ItemResponse
import org.jihye.acnhhb.data.remote.dto.SeaCreatureResponse
import org.jihye.acnhhb.data.remote.dto.ToolResponse
import org.jihye.acnhhb.data.remote.dto.VillagerResponse

class RemoteDataSource(
    private val client: NookipediaNetwork,
) {
    suspend fun fetchVillagers(
        species: String?,
        personality: String?,
        game: String?,
        isNhDetails: String?,
    ): List<VillagerResponse> {
        val params = mapOf(
            "species" to species,
            "personality" to personality,
            "game" to game,
            "nhdetails" to isNhDetails,
        )
        return client.get("/villagers", params)
    }

    suspend fun fetchFishes(
        month: String?,
        isExcludeDetails: String?,
        thumbnailSize: Int?,
    ): List<FishResponse> {
        val params = mapOf(
            "month" to month,
            "excludedetails" to isExcludeDetails,
            "thumbsize" to thumbnailSize?.toString(),
        )
        return client.get("/nh/fish", params)
    }

    suspend fun fetchBugs(
        month: String?,
        isExcludeDetails: String?,
        thumbnailSize: Int?,
    ): List<BugResponse> {
        val params = mapOf(
            "month" to month,
            "excludedetails" to isExcludeDetails,
            "thumbsize" to thumbnailSize?.toString(),
        )
        return client.get("/nh/bugs", params)
    }

    suspend fun fetchSeaCreatures(
        month: String?,
        isExcludeDetails: String?,
        thumbnailSize: Int?,
    ): List<SeaCreatureResponse> {
        val params = mapOf(
            "month" to month,
            "excludedetails" to isExcludeDetails,
            "thumbsize" to thumbnailSize?.toString(),
        )
        return client.get("/nh/sea", params)
    }

    suspend fun fetchArt(
        isExcludeFakes: String?,
        thumbnailSize: Int?,
    ): List<ArtResponse> {
        val params = mapOf(
            "excludefakes" to isExcludeFakes,
            "thumbsize" to thumbnailSize?.toString(),
        )
        return client.get("/nh/art", params)
    }

    suspend fun fetchFossils(
        thumbnailSize: Int?,
    ): List<FossilResponse> {
        val params = mapOf(
            "thumbsize" to thumbnailSize?.toString(),
        )
        return client.get("/nh/fossils/individuals", params)
    }

    suspend fun fetchItems(
        thumbnailSize: Int?,
        isExcludeDetails: String?,
    ): List<ItemResponse> {
        val params = mapOf(
            "thumbsize" to thumbnailSize?.toString(),
            "excludedetails" to isExcludeDetails,
        )
        return client.get("/nh/items", params)
    }

    suspend fun fetchGyroids(
        sound: String?,
        isExcludeDetails: String?,
    ): List<GyroidResponse> {
        val params = mapOf(
            "sound" to sound,
            "excludedetails" to isExcludeDetails,
        )
        return client.get("/nh/gyroids", params)
    }

    suspend fun fetchTools(
        isExcludeDetails: String?,
    ): List<ToolResponse> {
        val params = mapOf(
            "excludedetails" to isExcludeDetails,
        )
        return client.get("/nh/tools", params)
    }

}
