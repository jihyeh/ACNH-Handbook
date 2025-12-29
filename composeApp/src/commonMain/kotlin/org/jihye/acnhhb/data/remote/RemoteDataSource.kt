package org.jihye.acnhhb.data.remote

import org.jihye.acnhhb.data.remote.dto.FishResponse
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
}
