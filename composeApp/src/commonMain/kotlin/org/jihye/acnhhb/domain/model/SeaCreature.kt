package org.jihye.acnhhb.domain.model

data class SeaCreature(
    val number: Int,
    val name: String,
    val imageUrl: String,
    val shadowSize: String,
    val shadowMovement: String,
    val rarity: String,
    val sellNook: Int,
    val northMonths: String,
    val southMonths: String,
    val catchphrases: List<String>,
)
