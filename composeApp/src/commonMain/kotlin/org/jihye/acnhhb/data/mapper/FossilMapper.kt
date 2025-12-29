package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.FossilResponse
import org.jihye.acnhhb.domain.model.Fossil

fun FossilResponse.toDomain(): Fossil {
    return Fossil(
        name = name,
        url = url ?: "",
        imageUrl = imageUrl,
        price = price,
        fossilGroup = fossilGroup,
        description = description ?: "",
        interactable = interactable ?: false,
        hhaBase = hhaBase ?: 0,
        width = width ?: 0,
        length = length ?: 0,
        colors = colors ?: emptyList(),
    )
}
