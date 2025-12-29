package org.jihye.acnhhb.ui.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Item
import org.jihye.acnhhb.domain.model.ItemCategory
import org.jihye.acnhhb.domain.repository.ItemRepository

class ItemViewModel(
    itemRepository: ItemRepository,
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow(ItemCategory.NONE)
    val selectedCategory: StateFlow<ItemCategory> = _selectedCategory.asStateFlow()

    private val items: Flow<Result<List<Item>>> =
        itemRepository.getItems().map {
            Result.success(it)
        }.catch {
            emit(Result.failure(it))
        }

    val state: StateFlow<ItemListState> =
        combine(items, _selectedCategory) { itemsResult, category ->
            itemsResult.fold(
                onSuccess = { items ->
                    val filteredItems =
                        if (category == ItemCategory.NONE) {
                            items
                        } else {
                            items.filter { it.category == category }
                        }
                    ItemListState.Success(
                        items = items,
                        filteredItems = filteredItems,
                        selectedCategory = category
                    )
                },
                onFailure = { ItemListState.Error(it.message ?: "Unknown error") }
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ItemListState.Loading,
            )

    fun onCategorySelected(category: ItemCategory) {
        _selectedCategory.value = category
    }
}

sealed interface ItemListState {
    data object Loading : ItemListState
    data class Success(
        val items: List<Item>,
        val filteredItems: List<Item>,
        val selectedCategory: ItemCategory = ItemCategory.NONE,
    ) : ItemListState

    data class Error(val message: String) : ItemListState
}
