package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.ArtResponse
import org.jihye.acnhhb.domain.model.Art
import org.jihye.acnhhb.domain.model.ArtInfo as DomainArtInfo

fun ArtResponse.toDomain(localizedName: String? = null): Art {
    return Art(
        name = localizedName ?: name,
        url = url ?: "",
        imageUrl = imageUrl,
        hasFake = hasFake ?: false,
        buy = buy ?: 0,
        sell = sell ?: 0,
        artName = artName ?: "",
        artType = artType ?: "",
        year = year ?: "",
        artStyle = artStyle ?: "",
        availability = availability ?: "",
        width = width ?: 0.0,
        length = length ?: 0.0,
        author = author ?: "",
        description = description ?: "",
        realInfo = realInfo?.let {
            DomainArtInfo(
                imageUrl = it.imageUrl ?: "",
                textureUrl = it.textureUrl ?: "",
                description = it.description ?: ""
            )
        },
        fakeInfo = fakeInfo?.let {
            DomainArtInfo(
                imageUrl = it.imageUrl ?: "",
                textureUrl = it.textureUrl ?: "",
                description = it.description ?: ""
            )
        },
    )
}
