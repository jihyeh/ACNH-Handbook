package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.FossilResponse
import org.jihye.acnhhb.domain.model.Fossil

fun FossilResponse.toDomain(localizedName: String? = null): Fossil {
    return Fossil(
        name = localizedName ?: name,
        url = url ?: "",
        imageUrl = imageUrl,
        price = price,
        fossilGroup = fossilGroup,
        description = description ?: "",
        interactable = interactable ?: false,
        hhaBase = hhaBase ?: 0,
        width = width ?: 0f,
        length = length ?: 0f,
        colors = colors ?: emptyList(),
    )
}
