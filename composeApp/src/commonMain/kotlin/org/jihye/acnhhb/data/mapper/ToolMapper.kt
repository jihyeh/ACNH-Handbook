package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.ToolResponse
import org.jihye.acnhhb.domain.model.Availability as DomainAvailability
import org.jihye.acnhhb.domain.model.Buy as DomainBuy
import org.jihye.acnhhb.domain.model.Tool

fun ToolResponse.toDomain(localizedName: String? = null): Tool {
    return Tool(
        name = localizedName ?: name,
        url = url,
        imageUrl = variations?.firstOrNull()?.imageUrl ?: "",
        uses = uses?.toIntOrNull() ?: 0,
        hhaBase = hhaBase ?: 0,
        sell = sell ?: 0,
        customizable = customizable ?: false,
        customKits = customKits ?: 0,
        customBodyPart = customBodyPart ?: "",
        versionAdded = versionAdded ?: "",
        unlocked = unlocked ?: false,
        notes = notes ?: "",
        availability = availability?.map {
            DomainAvailability(from = it.from ?: "", note = it.note ?: "")
        } ?: emptyList(),
        buy = buy?.map {
            DomainBuy(price = it.price ?: 0, currency = it.currency ?: "")
        } ?: emptyList(),
        variations = variations?.map {
            Tool.Variation(variation = it.variation ?: "", imageUrl = it.imageUrl)
        } ?: emptyList()
    )
}
