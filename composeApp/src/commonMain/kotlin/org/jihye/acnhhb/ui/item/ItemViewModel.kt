package org.jihye.acnhhb.ui.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Item
import org.jihye.acnhhb.domain.repository.ItemRepository

class ItemViewModel(
    itemRepository: ItemRepository,
) : ViewModel() {
    val state: StateFlow<ItemListState> =
        itemRepository
            .getItems()
            .map { ItemListState.Success(it) as ItemListState }
            .onStart { emit(ItemListState.Loading) }
            .catch { emit(ItemListState.Error(it.message ?: "Unknown error")) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ItemListState.Loading,
            )
}

sealed interface ItemListState {
    data object Loading : ItemListState
    data class Success(val items: List<Item>) : ItemListState
    data class Error(val message: String) : ItemListState
}
