package org.jihye.acnhhb.domain.model

data class Fossil(
    val name: String,
    val url: String,
    val imageUrl: String,
    val price: Int,
    val fossilGroup: String,
    val description: String,
    val interactable: Boolean,
    val hhaBase: Int,
    val width: Float,
    val length: Float,
    val colors: List<String>,
)
