package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.FossilResponse
import org.jihye.acnhhb.domain.model.Fossil

fun FossilResponse.toDomain(): Fossil {
    return Fossil(
        name = name,
        imageUrl = imageUrl,
        price = price,
        fossilGroup = fossilGroup,
        description = description ?: "",
    )
}
