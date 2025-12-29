package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.BugResponse
import org.jihye.acnhhb.domain.model.Bug

fun BugResponse.toDomain(): Bug {
    return Bug(
        number = number,
        name = name,
        imageUrl = imageUrl,
        location = location,
        rarity = rarity,
        sellNook = sellNook,
        sellFlick = sellFlick,
        northMonths = north.months,
        southMonths = south.months,
        catchphrases = catchphrases
    )
}
