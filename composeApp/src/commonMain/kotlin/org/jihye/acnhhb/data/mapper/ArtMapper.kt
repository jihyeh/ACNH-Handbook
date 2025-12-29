package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.ArtResponse
import org.jihye.acnhhb.domain.model.Art

fun ArtResponse.toDomain(): Art {
    return Art(
        name = name,
        imageUrl = imageUrl,
        hasFake = hasFake,
        buy = buy,
        sell = sell,
        artName = artName,
        author = author,
        description = description,
    )
}
