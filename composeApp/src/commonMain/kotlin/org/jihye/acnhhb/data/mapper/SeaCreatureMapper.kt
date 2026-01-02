package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.SeaCreatureResponse
import org.jihye.acnhhb.domain.model.SeaCreature

fun SeaCreatureResponse.toDomain(localizedName: String? = null): SeaCreature {
    return SeaCreature(
        number = number,
        name = localizedName ?: name,
        url = url ?: "",
        imageUrl = imageUrl,
        renderUrl = renderUrl ?: "",
        shadowSize = shadowSize,
        shadowMovement = shadowMovement,
        rarity = rarity,
        sellNook = sellNook,
        northMonths = north.months,
        southMonths = south.months,
        catchphrases = catchphrases
    )
}
