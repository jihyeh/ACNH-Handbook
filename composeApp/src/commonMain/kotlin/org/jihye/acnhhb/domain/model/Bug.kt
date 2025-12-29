package org.jihye.acnhhb.domain.model

data class Bug(
    val number: Int,
    val name: String,
    val imageUrl: String,
    val location: String,
    val rarity: String,
    val sellNook: Int,
    val sellFlick: Int,
    val northMonths: String,
    val southMonths: String,
    val catchphrases: List<String>,
)
