package org.jihye.acnhhb.ui.settings

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.settings_close
import acnhhandbook.composeapp.generated.resources.settings_hemisphere
import acnhhandbook.composeapp.generated.resources.settings_hemisphere_north
import acnhhandbook.composeapp.generated.resources.settings_hemisphere_south
import acnhhandbook.composeapp.generated.resources.settings_theme
import acnhhandbook.composeapp.generated.resources.settings_theme_dark
import acnhhandbook.composeapp.generated.resources.settings_theme_light
import acnhhandbook.composeapp.generated.resources.settings_title
import acnhhandbook.composeapp.generated.resources.settings_version
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsDialog(
    viewModel: SettingsViewModel = koinViewModel(),
    onDismissRequest: () -> Unit = {},
) {
    val isDarkTheme by viewModel.isDarkTheme.collectAsStateWithLifecycle()
    val isNorth by viewModel.isNorth.collectAsStateWithLifecycle()
    val appVersion = viewModel.appVersion

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = stringResource(Res.string.settings_title),
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            SettingsContent(
                isDarkTheme = isDarkTheme,
                isNorth = isNorth,
                appVersion = appVersion,
                onThemeChange = { viewModel.updateTheme(it) },
                onHemisphereChange = { viewModel.updateHemisphere(it) }
            )
        },
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text(
                    text = stringResource(Res.string.settings_close),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    )
}

@Composable
private fun SettingsContent(
    isDarkTheme: Boolean,
    isNorth: Boolean,
    appVersion: String,
    onThemeChange: (Boolean) -> Unit = {},
    onHemisphereChange: (Boolean) -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Theme Setting
        Text(
            text = stringResource(Res.string.settings_theme),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        ThemeOption(
            label = stringResource(Res.string.settings_theme_light),
            selected = isDarkTheme.not(),
            onClick = { onThemeChange(false) }
        )
        ThemeOption(
            label = stringResource(Res.string.settings_theme_dark),
            selected = isDarkTheme,
            onClick = { onThemeChange(true) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Hemisphere Setting
        Text(
            text = stringResource(Res.string.settings_hemisphere),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        RadioButtonRow(
            label = stringResource(Res.string.settings_hemisphere_north),
            selected = isNorth,
            onClick = { onHemisphereChange(true) }
        )
        RadioButtonRow(
            label = stringResource(Res.string.settings_hemisphere_south),
            selected = !isNorth,
            onClick = { onHemisphereChange(false) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // App Version
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(Res.string.settings_version),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(text = "v$appVersion", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun ThemeOption(
    label: String,
    selected: Boolean,
    onClick: () -> Unit = {},
) {
    Row(
        Modifier.fillMaxWidth()
            .height(48.dp)
            .selectable(selected = selected, onClick = onClick, role = Role.RadioButton),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
private fun RadioButtonRow(label: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth()
            .height(48.dp)
            .selectable(selected = selected, onClick = onClick, role = Role.RadioButton),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsContentLightPreview() {
    MaterialTheme {
        SettingsContent(
            isDarkTheme = false,
            isNorth = true,
            appVersion = "1.0.0",
        )
    }
}