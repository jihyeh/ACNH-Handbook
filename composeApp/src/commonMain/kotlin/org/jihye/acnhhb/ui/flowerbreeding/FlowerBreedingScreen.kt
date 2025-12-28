package org.jihye.acnhhb.ui.flowerbreeding

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.home_flower_breed
import acnhhandbook.composeapp.generated.resources.ic_flower_bag_128
import acnhhandbook.composeapp.generated.resources.ic_flower_breed_150
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jihye.acnhhb.domain.model.BreedingRow
import org.jihye.acnhhb.domain.model.Flower
import org.jihye.acnhhb.domain.model.FlowerTypeData
import org.jihye.acnhhb.domain.model.SpecialBreeding
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowerBreedingScreen(
    viewModel: FlowerBreedingViewModel = koinViewModel(),
    onBack: () -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(onBackClick = onBack)
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.statusBars,
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            when (val currentState = state) {
                is FlowerBreedingState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is FlowerBreedingState.Error -> {
                    Text(
                        text = currentState.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is FlowerBreedingState.Success -> {
                    FlowerBreedingContent(
                        flowerGroups = currentState.data.breeding,
                        specialFlowers = currentState.data.special,
                    )
                }
            }
        }
    }
}

@Composable
fun FlowerBreedingContent(
    flowerGroups: List<FlowerTypeData>,
    specialFlowers: List<SpecialBreeding>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(flowerGroups) { flowerData ->
            FlowerBreedTableCard(flowerData)
        }
        item {
            SpecialFlowerTableCard(specialFlowers)
        }
    }
}

@Composable
fun FlowerBreedTableCard(table: FlowerTypeData, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        // Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = table.flowerType.replace("_", " "),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Parent 1",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
            VerticalDivider()
            Text(
                text = "Parent 2",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
            VerticalDivider()
            Text(
                text = "Offspring",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
            VerticalDivider()
            Text(
                text = "Chance",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
        }

        // Rows
        Column {
            table.rows.forEachIndexed { index, row ->
                if (index > 0) {
                    HorizontalDivider()
                }
                FlowerBreedRowItem(row)
            }
        }
    }
}

@Composable
private fun FlowerBreedRowItem(
    row: BreedingRow,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Flower 1
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            FlowerCell(row.parent1)
        }

        VerticalDivider()

        // Flower 2
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            FlowerCell(row.parent2)
        }

        VerticalDivider()

        // Offspring
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            FlowerCell(row.offspring)
        }

        VerticalDivider()

        // Chance
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${row.chance.toInt()}%",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun VerticalDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.outlineVariant)
    )
}

@Composable
private fun HorizontalDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(MaterialTheme.colorScheme.outlineVariant)
    )
}

@Composable
private fun FlowerCell(flower: Flower) {
    Box(
        contentAlignment = Alignment.TopEnd
    ) {
        FlowerIcon(flower)
        if (flower.fromFlowerBag) {
            Image(
                painter = painterResource(Res.drawable.ic_flower_bag_128),
                contentDescription = "From Bag",
                modifier = Modifier
                    .size(20.dp)
                    .offset(x = 4.dp, y = (-4).dp)
            )
        }
    }
}

@Composable
private fun FlowerIcon(
    flower: Flower,
    modifier: Modifier = Modifier,
) {
    val resource = flower.iconRes ?: return
    Image(
        painter = painterResource(resource),
        contentDescription = "${flower.color} ${flower.type}",
        modifier = modifier.size(48.dp)
    )
}

@Composable
private fun SpecialFlowerTableCard(
    rows: List<SpecialBreeding>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                2.dp,
                MaterialTheme.colorScheme.primaryContainer,
                RoundedCornerShape(12.dp)
            )
    ) {
        // Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Special flowers",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primaryContainer.copy(
                        alpha = 0.3f
                    )
                )
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Flower",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(24.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )
            Text(
                text = "Conditions",
                modifier = Modifier.weight(1.5f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(24.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )
            Text(
                text = "Offspring",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
            )
        }

        // Divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )

        // Rows
        rows.forEachIndexed { index, row ->
            SpecialFlowerRowItem(row)
            if (index < rows.size - 1) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
                )
            }
        }
    }
}

@Composable
private fun SpecialFlowerRowItem(
    row: SpecialBreeding,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Flower
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(), contentAlignment = Alignment.Center
        ) {
            FlowerCell(row.flower)
        }

        VerticalDivider()

        // Conditions
        Box(
            modifier = Modifier
                .weight(1.5f)
                .fillMaxHeight()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                row.conditionIcon?.let {
                    Image(
                        painter = painterResource(it),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
                row.condition?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        maxLines = 3
                    )
                }
            }
        }

        VerticalDivider()

        // Offspring
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(), contentAlignment = Alignment.Center
        ) {
            row.offspring?.let {
                FlowerCell(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_flower_breed_150),
                    contentDescription = stringResource(Res.string.home_flower_breed),
                    tint = Color.Unspecified,
                    modifier = Modifier.wrapContentSize(),
                )
                Text(
                    text = stringResource(Res.string.home_flower_breed),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurface
            ),
    )
}
