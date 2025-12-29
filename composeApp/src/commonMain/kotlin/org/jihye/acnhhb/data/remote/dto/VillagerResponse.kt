package org.jihye.acnhhb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VillagerResponse(
    val url: String? = null,
    val name: String,
    @SerialName("alt_name")
    val altName: String? = null,
    @SerialName("title_color")
    val titleColor: String? = null,
    @SerialName("text_color")
    val textColor: String? = null,
    val id: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    val species: String,
    val personality: String,
    val gender: String,
    @SerialName("birthday_month")
    val birthdayMonth: String? = null,
    @SerialName("birthday_day")
    val birthdayDay: String? = null,
    val sign: String? = null,
    val quote: String? = null,
    val phrase: String? = null,
    val clothing: String? = null,
    val islander: Boolean? = null,
    val debut: String? = null,
    @SerialName("prev_phrases")
    val prevPhrases: List<String> = emptyList(),
    val appearances: List<String> = emptyList(),
    @SerialName("nh_details")
    val nhDetails: NhDetails? = null,
)

@Serializable
data class NhDetails(
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("photo_url")
    val photoUrl: String? = null,
    @SerialName("icon_url")
    val iconUrl: String? = null,
    val quote: String? = null,
    @SerialName("sub-personality")
    val subPersonality: String? = null,
    val catchphrase: String? = null,
    val clothing: String? = null,
    @SerialName("clothing_variation")
    val clothingVariation: String? = null,
    @SerialName("fav_styles")
    val favStyles: List<String> = emptyList(),
    @SerialName("fav_colors")
    val favColors: List<String> = emptyList(),
    val hobby: String? = null,
    @SerialName("house_interior_url")
    val houseInteriorUrl: String? = null,
    @SerialName("house_exterior_url")
    val houseExteriorUrl: String? = null,
    @SerialName("house_wallpaper")
    val houseWallpaper: String? = null,
    @SerialName("house_flooring")
    val houseFlooring: String? = null,
    @SerialName("house_music")
    val houseMusic: String? = null,
    @SerialName("house_music_note")
    val houseMusicNote: String? = null,
)
