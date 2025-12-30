package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.FurnitureResponse
import org.jihye.acnhhb.domain.model.Furniture

fun FurnitureResponse.toDomain(): Furniture {
    return Furniture(
        name = name,
        url = url,
        imageUrl = variations?.firstOrNull()?.imageUrl ?: "",
        category = category ?: "",
        itemSeries = itemSeries ?: "",
        itemSet = itemSet ?: "",
        themes = themes ?: emptyList(),
        hhaCategory = hhaCategory ?: "",
        hhaBase = hhaBase ?: 0,
        tag = tag ?: "",
        lucky = lucky ?: false,
        luckySeason = luckySeason ?: "",
        buy = buy?.map {
            Furniture.Buy(price = it.price ?: 0, currency = it.currency ?: "")
        } ?: emptyList(),
        sell = sell ?: 0,
        variationTotal = variationTotal ?: 0,
        patternTotal = patternTotal ?: 0,
        customizable = customizable ?: false,
        customKits = customKits ?: 0,
        customKitType = customKitType ?: "",
        customBodyPart = customBodyPart ?: "",
        customPatternPart = customPatternPart ?: "",
        gridWidth = gridWidth ?: 0f,
        gridLength = gridLength ?: 0f,
        height = height ?: 0f,
        doorDecor = doorDecor ?: false,
        versionAdded = versionAdded ?: "",
        unlocked = unlocked ?: false,
        functions = functions ?: emptyList(),
        availability = availability?.map {
            Furniture.Availability(from = it.from ?: "", note = it.note ?: "")
        } ?: emptyList(),
        variations = variations?.map {
            Furniture.Variation(
                variation = it.variation ?: "",
                pattern = it.pattern ?: "",
                imageUrl = it.imageUrl,
                colors = it.colors ?: emptyList()
            )
        } ?: emptyList()
    )
}
