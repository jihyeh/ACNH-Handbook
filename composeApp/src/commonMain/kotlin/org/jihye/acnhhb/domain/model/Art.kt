package org.jihye.acnhhb.domain.model

data class Art(
        val name: String,
        val url: String,
        val imageUrl: String,
        val hasFake: Boolean,
        val buy: Int,
        val sell: Int,
        val artName: String,
        val artType: String,
        val year: String,
        val artStyle: String,
        val availability: String,
        val width: Double,
        val length: Double,
        val author: String,
        val description: String,
        val realInfo: ArtInfo?,
        val fakeInfo: ArtInfo?,
)

data class ArtInfo(
        val imageUrl: String,
        val textureUrl: String,
        val description: String,
)
