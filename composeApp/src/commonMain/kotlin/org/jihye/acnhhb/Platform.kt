package org.jihye.acnhhb

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform