package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.InteriorResponse
import org.jihye.acnhhb.domain.model.Interior

fun InteriorResponse.toDomain(): Interior {
    return Interior(
        name = name,
        url = url,
        imageUrl = imageUrl ?: "",
        category = category ?: "",
        itemSeries = itemSeries ?: "",
        itemSet = itemSet ?: "",
        themes = themes ?: emptyList(),
        hhaCategory = hhaCategory ?: "",
        hhaBase = hhaBase ?: 0,
        tag = tag ?: "",
        sell = sell ?: 0,
        versionAdded = versionAdded ?: "",
        unlocked = unlocked ?: false,
        notes = notes ?: "",
        gridWidth = gridWidth ?: 0f,
        gridLength = gridLength ?: 0f,
        colors = colors ?: emptyList(),
        availability = availability?.map {
            Interior.Availability(from = it.from ?: "", note = it.note ?: "")
        } ?: emptyList(),
        buy = buy?.map {
            Interior.Buy(price = it.price ?: 0, currency = it.currency ?: "")
        } ?: emptyList()
    )
}
