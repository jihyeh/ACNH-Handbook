package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.PhotoResponse
import org.jihye.acnhhb.domain.model.Photo

fun PhotoResponse.toDomain(): Photo {
    return Photo(
        name = name,
        url = url,
        category = category,
        imageUrl = variations?.firstOrNull()?.imageUrl ?: "",
        sell = sell ?: 0,
        buy = buy?.map {
            Photo.Buy(price = it.price ?: 0, currency = it.currency ?: "")
        } ?: emptyList(),
        availability = availability?.map {
            Photo.Availability(from = it.from ?: "", note = it.note ?: "")
        } ?: emptyList()
    )
}
