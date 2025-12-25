package org.jihye.acnhhb.data.repository

import acnhhandbook.composeapp.generated.resources.Res
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jihye.acnhhb.domain.model.FlowerBreedingData
import org.jihye.acnhhb.domain.repository.FlowerBreedingRepository

class FlowerBreedingRepositoryImpl : FlowerBreedingRepository {
    @OptIn(ExperimentalResourceApi::class)
    override suspend fun getFlowerBreedingData(): FlowerBreedingData {
        val jsonString = Res.readBytes(FLOWER_BREEDING_ASSET_PATH).decodeToString()
        return Json.decodeFromString(jsonString)
    }

    companion object {
        private const val FLOWER_BREEDING_ASSET_PATH = "files/flower_breeding.json"
    }
}
