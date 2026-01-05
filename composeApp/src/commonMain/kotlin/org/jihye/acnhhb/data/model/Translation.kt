package org.jihye.acnhhb.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Translation(
    @SerialName("Id") val id: String,
    @SerialName("KRko") val krName: String? = null,
    @SerialName("USen") val enName: String? = null,
)
