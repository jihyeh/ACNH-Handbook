package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.FishResponse
import org.jihye.acnhhb.domain.model.Fish

fun FishResponse.toDomain(): Fish {
    return Fish(
        number = number,
        name = name,
        imageUrl = imageUrl,
        location = location,
        shadowSize = shadowSize,
        rarity = rarity,
        sellNook = sellNook,
        sellCj = sellCj,
        northMonths = north.months,
        southMonths = south.months,
        catchphrases = catchphrases
    )
}
