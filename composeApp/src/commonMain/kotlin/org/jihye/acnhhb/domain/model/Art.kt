package org.jihye.acnhhb.domain.model

data class Art(
    val name: String,
    val imageUrl: String,
    val hasFake: Boolean,
    val buy: Int,
    val sell: Int,
    val artName: String,
    val author: String,
    val description: String,
)
