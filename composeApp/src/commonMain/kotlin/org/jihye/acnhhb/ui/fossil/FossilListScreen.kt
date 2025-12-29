package org.jihye.acnhhb.ui.fossil

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.home_fossils
import acnhhandbook.composeapp.generated.resources.ic_home_fossil
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import org.jihye.acnhhb.domain.model.Fossil
import org.jihye.acnhhb.ui.components.ErrorContent
import org.jihye.acnhhb.ui.components.ListScreenTopBar
import org.jihye.acnhhb.ui.components.LoadingContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FossilListScreen(
    viewModel: FossilViewModel = koinViewModel(),
    onBack: () -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            ListScreenTopBar(
                title = stringResource(Res.string.home_fossils),
                icon = Res.drawable.ic_home_fossil,
                onBackClick = onBack
            )
        }
    ) { paddingValues ->
        when (val currentState = state) {
            is FossilListState.Loading -> {
                LoadingContent(modifier = Modifier.padding(paddingValues))
            }

            is FossilListState.Success -> {
                FossilListContent(
                    fossils = currentState.fossils,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is FossilListState.Error -> {
                ErrorContent(
                    message = currentState.message,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
fun FossilListContent(
    fossils: List<Fossil>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize(),
    ) { items(fossils) { fossil -> FossilItem(fossil = fossil) } }
}

@Composable
fun FossilItem(
    fossil: Fossil,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = fossil.imageUrl,
                contentDescription = fossil.name,
                contentScale = ContentScale.FillHeight,
                placeholder = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
                error = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
                modifier = modifier.clip(RoundedCornerShape(8.dp)).aspectRatio(1f)
            )
            Text(
                text = fossil.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
