package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.ItemResponse
import org.jihye.acnhhb.domain.model.Availability as DomainAvailability
import org.jihye.acnhhb.domain.model.Buy as DomainBuy
import org.jihye.acnhhb.domain.model.Item

fun ItemResponse.toDomain(): Item {
    return Item(
        name = name,
        url = url,
        imageUrl = imageUrl,
        stack = stack ?: 0,
        hhaBase = hhaBase ?: 0,
        sell = sell ?: 0,
        isFence = isFence ?: false,
        materialType = materialType ?: "",
        materialSeasonality = materialSeasonality ?: "",
        materialSort = materialSort ?: 0,
        materialNameSort = materialNameSort ?: 0,
        materialSeasonalitySort = materialSeasonalitySort ?: 0,
        edible = edible ?: false,
        plantType = plantType ?: "",
        versionAdded = versionAdded ?: "",
        unlocked = unlocked ?: false,
        notes = notes ?: "",
        availability = itemAvailability?.map {
            DomainAvailability(
                from = it.from ?: "",
                note = it.note ?: ""
            )
        } ?: emptyList(),
        buy = buy?.map {
            DomainBuy(
                price = it.price ?: 0,
                currency = it.currency ?: ""
            )
        } ?: emptyList(),
    )
}
