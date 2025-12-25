package org.jihye.acnhhb

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jihye.acnhhb.navigation.NavigationRoot
import org.jihye.acnhhb.theme.ACNHHandbookTheme


@Composable
@Preview
fun App() {
    ACNHHandbookTheme {
        NavigationRoot(
            modifier = Modifier.fillMaxSize(),
        )
    }
}