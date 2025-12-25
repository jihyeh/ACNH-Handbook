package org.jihye.acnhhb.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object Home : Route

    @Serializable
    data class List(val title: String) : Route

    @Serializable
    data object FlowerBreeding : Route
}
