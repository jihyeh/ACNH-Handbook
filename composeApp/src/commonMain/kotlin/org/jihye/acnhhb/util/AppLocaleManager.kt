package org.jihye.acnhhb.util

import androidx.compose.ui.text.intl.Locale

class AppLocaleManager {
    fun getSystemLanguage(): String {
        return Locale.current.language
    }

    fun getSystemRegion(): String {
        return Locale.current.region
    }

    fun isKorean(): Boolean {
        return getSystemLanguage() == "ko"
    }
}
