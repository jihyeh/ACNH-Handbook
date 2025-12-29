package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.GyroidResponse
import org.jihye.acnhhb.domain.model.Gyroid

fun GyroidResponse.toDomain(): Gyroid {
    return Gyroid(
        name = name,
        url = url,
        imageUrl = variations?.firstOrNull()?.imageUrl ?: "",
        sell = sell,
        price = buy?.firstOrNull()?.price,
        variations = variations?.mapNotNull { it.variation } ?: emptyList()
    )
}
