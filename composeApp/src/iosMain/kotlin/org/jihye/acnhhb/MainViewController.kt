package org.jihye.acnhhb

import androidx.compose.ui.window.ComposeUIViewController
import org.jihye.acnhhb.di.startDependencyInjection

fun MainViewController() = ComposeUIViewController {
    KoinInitializer.init()
    App()
}

private object KoinInitializer {
    private var isInit = false
    fun init() {
        if (!isInit) {
            startDependencyInjection()
            isInit = true
        }
    }
}
