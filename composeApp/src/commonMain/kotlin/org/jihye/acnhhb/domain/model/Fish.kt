package org.jihye.acnhhb.domain.model

data class Fish(
    val number: Int,
    val name: String,
    val imageUrl: String,
    val location: String,
    val shadowSize: String,
    val rarity: String,
    val sellNook: Int,
    val sellCj: Int,
    val time: String,
    val northMonths: String,
    val southMonths: String,
    val catchphrases: List<String>,
)
