package org.jihye.acnhhb.domain.model

data class Tool(
    val name: String,
    val url: String,
    val imageUrl: String,
    val uses: Int,
    val hhaBase: Int,
    val sell: Int,
    val customizable: Boolean,
    val customKits: Int,
    val customBodyPart: String,
    val versionAdded: String,
    val unlocked: Boolean,
    val notes: String,
    val availability: List<Availability>,
    val buy: List<Buy>,
    val variations: List<Variation>,
) {
    data class Variation(
        val variation: String,
        val imageUrl: String,
    )
}
