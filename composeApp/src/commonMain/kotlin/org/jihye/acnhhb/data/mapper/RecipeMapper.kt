package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.RecipeResponse
import org.jihye.acnhhb.domain.model.Recipe

fun RecipeResponse.toDomain(localizedName: String? = null): Recipe {
    return Recipe(
        name = localizedName ?: name,
        url = url,
        imageUrl = imageUrl ?: "",
        serialId = serialId ?: 0,
        buy = buy?.map {
            Recipe.Buy(price = it.price ?: 0, currency = it.currency ?: "")
        } ?: emptyList(),
        sell = sell ?: 0,
        recipesToUnlock = recipesToUnlock ?: 0,
        availability = availability?.map {
            Recipe.Availability(from = it.from ?: "", note = it.note ?: "")
        } ?: emptyList(),
        materials = materials?.map {
            Recipe.Material(name = it.name, count = it.count)
        } ?: emptyList()
    )
}
