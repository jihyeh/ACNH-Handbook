package org.jihye.acnhhb.ui.furniture

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.home_furniture
import acnhhandbook.composeapp.generated.resources.ic_home_furniture
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import org.jihye.acnhhb.domain.model.Furniture
import org.jihye.acnhhb.ui.components.ErrorContent
import org.jihye.acnhhb.ui.components.ListScreenTopBar
import org.jihye.acnhhb.ui.components.LoadingContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FurnitureListScreen(
    viewModel: FurnitureViewModel = koinViewModel(),
    onBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            ListScreenTopBar(
                title = stringResource(Res.string.home_furniture),
                icon = Res.drawable.ic_home_furniture,
                onBackClick = onBack
            )
        }
    ) { paddingValues ->
        when (val currentState = state) {
            is FurnitureListState.Loading -> {
                LoadingContent(modifier = Modifier.padding(paddingValues))
            }

            is FurnitureListState.Success -> {
                FurnitureListContent(
                    furniture = currentState.furniture,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is FurnitureListState.Error -> {
                ErrorContent(
                    message = currentState.message,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
fun FurnitureListContent(
    furniture: List<Furniture>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = furniture,
            key = { it.name } // Assuming name is unique enough for now, or use a combination
        ) { item ->
            FurnitureItem(
                furniture = item,
                onClick = { /* TODO: Navigate to detail */ }
            )
        }
    }
}

@Composable
fun FurnitureItem(
    furniture: Furniture,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = furniture.imageUrl,
                contentDescription = furniture.name,
                modifier = Modifier.fillMaxWidth().aspectRatio(1f),
                contentScale = ContentScale.Fit
            )
            Text(
                text = furniture.name,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
