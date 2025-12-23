package org.jihye.acnhhb

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jihye.acnhhb.theme.ACNHHandbookTheme
import org.jihye.acnhhb.ui.home.HomeScreen

@Composable
@Preview
fun App() {
    ACNHHandbookTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(navController = navController)
            }

            val routes = listOf(
                "villagers",
                "bugs",
                "fish",
                "sea_creatures",
                "art",
                "fossils",
                "items",
                "events",
                "flowers"
            )

            routes.forEach { route ->
                composable(route) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Route: $route", color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        }
    }
}
