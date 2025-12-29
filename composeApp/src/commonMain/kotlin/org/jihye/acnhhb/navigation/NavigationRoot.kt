package org.jihye.acnhhb.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.jihye.acnhhb.ui.art.ArtListScreen
import org.jihye.acnhhb.ui.bug.BugListScreen
import org.jihye.acnhhb.ui.fish.FishListScreen
import org.jihye.acnhhb.ui.flowerbreeding.FlowerBreedingScreen
import org.jihye.acnhhb.ui.fossil.FossilListScreen
import org.jihye.acnhhb.ui.gyroid.GyroidListScreen
import org.jihye.acnhhb.ui.home.Category
import org.jihye.acnhhb.ui.home.HomeScreen
import org.jihye.acnhhb.ui.item.ItemListScreen
import org.jihye.acnhhb.ui.sea.SeaCreatureListScreen
import org.jihye.acnhhb.ui.villager.VillagerListScreen

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(
        configuration = config,
        Route.Home
    )

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider =
            entryProvider {
                entry<Route.Home> {
                    HomeScreen { item ->
                        when (item) {
                            Category.FLOWER_BREED -> {
                                backStack.add(Route.FlowerBreeding)
                            }

                            else -> {
                                backStack.add(Route.List(item.name))
                            }
                        }
                    }
                }

                entry<Route.FlowerBreeding> {
                    FlowerBreedingScreen {
                        backStack.removeLastOrNull()
                    }
                }

                entry<Route.List> { list ->
                    when (list.title) {
                        Category.VILLAGERS.name -> {
                            VillagerListScreen {
                                backStack.removeLastOrNull()
                            }
                        }

                        Category.FISH.name -> {
                            FishListScreen {
                                backStack.removeLastOrNull()
                            }
                        }

                        Category.BUGS.name -> {
                            BugListScreen {
                                backStack.removeLastOrNull()
                            }
                        }

                        Category.SEA_CREATURES.name -> {
                            SeaCreatureListScreen {
                                backStack.removeLastOrNull()
                            }
                        }

                        Category.ART.name -> {
                            ArtListScreen {
                                backStack.removeLastOrNull()
                            }
                        }

                        Category.FOSSILS.name -> {
                            FossilListScreen {
                                backStack.removeLastOrNull()
                            }
                        }

                        Category.ITEMS.name -> {
                            ItemListScreen {
                                backStack.removeLastOrNull()
                            }
                        }

                        Category.GYROIDS.name -> {
                            GyroidListScreen {
                                backStack.removeLastOrNull()
                            }
                        }

                        else -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.background),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "Screen: ${list.title}",
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }
    )
}
