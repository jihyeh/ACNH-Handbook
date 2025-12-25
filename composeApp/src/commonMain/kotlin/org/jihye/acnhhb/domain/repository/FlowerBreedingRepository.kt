package org.jihye.acnhhb.domain.repository

import org.jihye.acnhhb.domain.model.FlowerBreedingData

interface FlowerBreedingRepository {
    suspend fun getFlowerBreedingData(): FlowerBreedingData
}
