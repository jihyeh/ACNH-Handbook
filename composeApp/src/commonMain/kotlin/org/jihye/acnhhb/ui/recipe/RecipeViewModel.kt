package org.jihye.acnhhb.ui.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Recipe
import org.jihye.acnhhb.domain.repository.RecipeRepository

class RecipeViewModel(
    recipeRepository: RecipeRepository,
) : ViewModel() {

    val state: StateFlow<RecipeListState> =
        recipeRepository
            .getRecipes(material = null, isExcludeDetails = null, thumbnailSize = null)
            .map { recipes -> RecipeListState.Success(recipes) as RecipeListState }
            .catch { exception ->
                emit(RecipeListState.Error(exception.message ?: "Unknown error"))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = RecipeListState.Loading,
            )
}

sealed interface RecipeListState {
    data object Loading : RecipeListState
    data class Success(val recipes: List<Recipe>) : RecipeListState
    data class Error(val message: String) : RecipeListState
}
