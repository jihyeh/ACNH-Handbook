package org.jihye.acnhhb.ui.event

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.home_events
import acnhhandbook.composeapp.generated.resources.ic_home_event
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import org.jetbrains.compose.resources.stringResource
import org.jihye.acnhhb.domain.model.Event
import org.jihye.acnhhb.ui.components.ErrorContent
import org.jihye.acnhhb.ui.components.ListScreenTopBar
import org.jihye.acnhhb.ui.components.LoadingContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EventListScreen(
    viewModel: EventViewModel = koinViewModel(),
    onBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            ListScreenTopBar(
                title = stringResource(Res.string.home_events),
                icon = Res.drawable.ic_home_event,
                onBackClick = onBack
            )
        }
    ) { paddingValues ->
        when (val currentState = state) {
            is EventListState.Loading -> {
                LoadingContent(modifier = Modifier.padding(paddingValues))
            }

            is EventListState.Success -> {
                EventListContent(
                    events = currentState.events,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is EventListState.Error -> {
                ErrorContent(
                    message = currentState.message,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventListContent(
    events: List<Event>,
    modifier: Modifier = Modifier,
) {
    val groupedEvents =
        remember(events) {
            events.groupBy { it.date.substring(0, 7) }
        } // YYYY-MM grouping

    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = modifier
    ) {
        groupedEvents.forEach { (month, monthEvents) ->
            stickyHeader {
                MonthHeader(month = month)
            }

            items(
                items = monthEvents,
                key = { "${it.date}-${it.name}" }
            ) { event ->
                EventItem(event = event)
            }
        }
    }
}

@Composable
fun MonthHeader(
    month: String,
    modifier: Modifier = Modifier,
) {
    val parsedDate = remember(month) { LocalDate.parse("$month-01") }
    val formattedMonth =
        remember(parsedDate) {
            parsedDate.format(
                LocalDate.Format {
                    year()
                    char(' ')
                    monthName(MonthNames.ENGLISH_FULL)
                }
            )
        }

    Box(
        modifier = modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = formattedMonth,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun EventItem(
    event: Event,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = event.date.substring(8), // DD
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = event.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        EventTypeBadge(type = event.type)
    }
}

@Composable
fun EventTypeBadge(
    type: Event.EventType,
    modifier: Modifier = Modifier,
) {
    val (icon, color) =
        when (type) {
            Event.EventType.BIRTHDAY -> "🎂" to MaterialTheme.colorScheme.tertiary
            Event.EventType.EVENT -> "🎉" to MaterialTheme.colorScheme.primary
            Event.EventType.RECIPES -> "📜" to MaterialTheme.colorScheme.secondary
            Event.EventType.NOOK_SHOPPING -> "🛍️" to MaterialTheme.colorScheme.error
            else -> "📅" to MaterialTheme.colorScheme.outline
        }

    Text(text = icon, style = MaterialTheme.typography.bodyLarge, modifier = modifier)
}
