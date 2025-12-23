package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.VillagerResponse
import org.jihye.acnhhb.domain.model.Villager

fun VillagerResponse.toDomain(): Villager {
    return Villager(
        id = id,
        name = name,
        url = url,
        altName = altName,
        titleColor = titleColor,
        textColor = textColor,
        imageUrl = imageUrl,
        species = species,
        personality = personality,
        gender = gender,
        birthdayMonth = birthdayMonth,
        birthdayDay = birthdayDay,
        sign = sign,
        quote = quote,
        phrase = phrase,
        clothing = clothing,
        islander = islander,
        debut = debut
    )
}