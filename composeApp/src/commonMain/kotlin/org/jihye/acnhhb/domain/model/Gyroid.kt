package org.jihye.acnhhb.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Gyroid(
    val name: String,
    val url: String,
    val imageUrl: String,
    val sell: Int? = null,
    val price: Int? = null,
    val variations: List<String> = emptyList(),
)
