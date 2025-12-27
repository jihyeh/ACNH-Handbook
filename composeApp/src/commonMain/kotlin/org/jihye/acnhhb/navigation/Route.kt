package org.jihye.acnhhb.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object Home : Route

    @Serializable
    data class List(val title: String) : Route

    @Serializable
    data object FlowerBreeding : Route
}

val config = SavedStateConfiguration {
    serializersModule = routeSerializersModule
}

/**
 * Navigation3가 Route의 하위 클래스들을 인식할 수 있도록
 * SerializersModule을 정의합니다. (필요 시 NavHost 설정에 주입)
 */
val routeSerializersModule = SerializersModule {
    polymorphic(NavKey::class) {
        subclass(Route.Home::class)
        subclass(Route.List::class)
        subclass(Route.FlowerBreeding::class)
    }
}
