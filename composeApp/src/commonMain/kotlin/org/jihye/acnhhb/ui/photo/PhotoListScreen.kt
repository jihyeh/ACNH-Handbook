package org.jihye.acnhhb.ui.photo

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.home_photos
import acnhhandbook.composeapp.generated.resources.ic_home_photo
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
import org.jihye.acnhhb.domain.model.Photo
import org.jihye.acnhhb.ui.components.ErrorContent
import org.jihye.acnhhb.ui.components.ListScreenTopBar
import org.jihye.acnhhb.ui.components.LoadingContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PhotoListScreen(
    viewModel: PhotoViewModel = koinViewModel(),
    onBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            ListScreenTopBar(
                title = stringResource(Res.string.home_photos),
                icon = Res.drawable.ic_home_photo,
                onBackClick = onBack
            )
        }
    ) { paddingValues ->
        when (val currentState = state) {
            is PhotoListState.Loading -> {
                LoadingContent(modifier = Modifier.padding(paddingValues))
            }

            is PhotoListState.Success -> {
                PhotoListContent(
                    photos = currentState.photos,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is PhotoListState.Error -> {
                ErrorContent(
                    message = currentState.message,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
fun PhotoListContent(
    photos: List<Photo>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(items = photos, key = { it.name }) { item ->
            PhotoItem(photo = item, onClick = { /* TODO: Navigate to detail */ })
        }
    }
}

@Composable
fun PhotoItem(
    photo: Photo,
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
                model = photo.imageUrl,
                contentDescription = photo.name,
                modifier = Modifier.fillMaxWidth().aspectRatio(1f),
                contentScale = ContentScale.Fit
            )
            Text(
                text = photo.name,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
