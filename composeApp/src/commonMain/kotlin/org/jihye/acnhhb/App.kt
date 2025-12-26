package org.jihye.acnhhb

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jihye.acnhhb.navigation.NavigationRoot
import org.jihye.acnhhb.theme.ACNHHandbookTheme
import org.jihye.acnhhb.ui.settings.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val settingsViewModel = koinViewModel<SettingsViewModel>()
    val isDarkTheme by settingsViewModel.isDarkTheme.collectAsStateWithLifecycle()

    ACNHHandbookTheme(darkTheme = isDarkTheme) {
        NavigationRoot(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
