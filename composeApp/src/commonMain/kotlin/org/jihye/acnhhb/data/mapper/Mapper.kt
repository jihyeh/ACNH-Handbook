package org.jihye.acnhhb.data.mapper

import org.jihye.acnhhb.data.remote.dto.NhDetails as RemoteNhDetails
import org.jihye.acnhhb.data.remote.dto.VillagerResponse
import org.jihye.acnhhb.domain.model.NhDetails
import org.jihye.acnhhb.domain.model.Villager

fun VillagerResponse.toDomain(
    localizedName: String? = null,
): Villager {
    return Villager(
        id = id,
        name = localizedName ?: name,
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
        debut = debut,
        prevPhrases = prevPhrases,
        appearances = appearances,
        nhDetails = nhDetails?.toDomain()
    )
}

fun RemoteNhDetails.toDomain(): NhDetails {
    return NhDetails(
        imageUrl = imageUrl,
        photoUrl = photoUrl,
        iconUrl = iconUrl,
        quote = quote,
        subPersonality = subPersonality,
        catchphrase = catchphrase,
        clothing = clothing,
        clothingVariation = clothingVariation,
        favStyles = favStyles,
        favColors = favColors,
        hobby = hobby,
        houseInteriorUrl = houseInteriorUrl,
        houseExteriorUrl = houseExteriorUrl,
        houseWallpaper = houseWallpaper,
        houseFlooring = houseFlooring,
        houseMusic = houseMusic,
        houseMusicNote = houseMusicNote
    )
}
