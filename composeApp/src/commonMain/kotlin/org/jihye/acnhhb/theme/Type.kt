package org.jihye.acnhhb.theme

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.pretendard_bold
import acnhhandbook.composeapp.generated.resources.pretendard_light
import acnhhandbook.composeapp.generated.resources.pretendard_medium
import acnhhandbook.composeapp.generated.resources.pretendard_regular
import acnhhandbook.composeapp.generated.resources.pretendard_thin
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font

val pretendard
    @Composable get() =
        FontFamily(
            Font(Res.font.pretendard_regular, FontWeight.Normal, FontStyle.Normal),
            Font(Res.font.pretendard_bold, FontWeight.Bold, FontStyle.Normal),
            Font(Res.font.pretendard_light, FontWeight.Light, FontStyle.Normal),
            Font(Res.font.pretendard_thin, FontWeight.Thin, FontStyle.Normal),
            Font(Res.font.pretendard_medium, FontWeight.Medium, FontStyle.Normal),
        )

// Default Typography from Material 3
private val defaultTypography = Typography()

// Set of Material typography styles to start with
val Typography
    @Composable get() =
        Typography(
            displayLarge = defaultTypography.displayLarge.copy(fontFamily = pretendard),
            displayMedium = defaultTypography.displayMedium.copy(fontFamily = pretendard),
            displaySmall = defaultTypography.displaySmall.copy(fontFamily = pretendard),
            headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = pretendard),
            headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = pretendard),
            headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = pretendard),
            titleLarge = defaultTypography.titleLarge.copy(fontFamily = pretendard),
            titleMedium = defaultTypography.titleMedium.copy(fontFamily = pretendard),
            titleSmall = defaultTypography.titleSmall.copy(fontFamily = pretendard),
            bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = pretendard),
            bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = pretendard),
            bodySmall = defaultTypography.bodySmall.copy(fontFamily = pretendard),
            labelLarge = defaultTypography.labelLarge.copy(fontFamily = pretendard),
            labelMedium = defaultTypography.labelMedium.copy(fontFamily = pretendard),
            labelSmall = defaultTypography.labelSmall.copy(fontFamily = pretendard)
        )
