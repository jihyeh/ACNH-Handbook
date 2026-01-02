package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.ClothingResponse
import org.jihye.acnhhb.domain.model.Availability as DomainAvailability
import org.jihye.acnhhb.domain.model.Buy as DomainBuy
import org.jihye.acnhhb.domain.model.Clothing

fun ClothingResponse.toDomain(localizedName: String? = null): Clothing {
    return Clothing(
            name = localizedName ?: name,
            url = url,
            imageUrl = variations?.firstOrNull()?.imageUrl ?: "",
            category = category ?: "",
            sell = sell ?: 0,
            variationTotal = variationTotal ?: 0,
            isVillagerEquippable = isVillagerEquippable ?: false,
            seasonality = seasonality ?: "",
            versionAdded = versionAdded ?: "",
            unlocked = unlocked ?: false,
            notes = notes ?: "",
            labelThemes = labelThemes ?: emptyList(),
            styles = styles ?: emptyList(),
            availability =
                    availability?.map {
                        DomainAvailability(from = it.from ?: "", note = it.note ?: "")
                    }
                            ?: emptyList(),
            buy = buy?.map { DomainBuy(price = it.price ?: 0, currency = it.currency ?: "") }
                            ?: emptyList(),
            variations =
                    variations?.map {
                        Clothing.Variation(
                                variation = it.variation ?: "",
                                imageUrl = it.imageUrl,
                                colors = it.colors ?: emptyList()
                        )
                    }
                            ?: emptyList()
    )
}
