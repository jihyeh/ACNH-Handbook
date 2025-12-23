package org.jihye.acnhhb.domain.model

data class Villager(
    val id: String?,
    val name: String,
    val url: String?,
    val altName: String?,
    val titleColor: String?,
    val textColor: String?,
    val imageUrl: String?,
    val species: String,
    val personality: String,
    val gender: String,
    val birthdayMonth: String?,
    val birthdayDay: String?,
    val sign: String?,
    val quote: String?,
    val phrase: String?,
    val clothing: String?,
    val islander: Boolean?,
    val debut: String?,
)
