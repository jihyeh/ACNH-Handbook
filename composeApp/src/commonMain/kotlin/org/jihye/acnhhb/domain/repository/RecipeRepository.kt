package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jihye.acnhhb.domain.model.Recipe

interface RecipeRepository {
    fun getRecipes(
        material: String? = null,
        isExcludeDetails: String? = null,
        thumbnailSize: Int? = null,
    ): Flow<List<Recipe>>
}
