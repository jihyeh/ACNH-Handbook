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
)
