package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Recipe
import org.jihye.acnhhb.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val recipeNameProvider: RecipeNameProvider,
) : RecipeRepository {
    override fun getRecipes(
        material: String?,
        isExcludeDetails: String?,
        thumbnailSize: Int?,
    ): Flow<List<Recipe>> = flow {
        coroutineScope {
            val remoteRecipesDeferred = async {
                remoteDataSource.fetchRecipes(
                    material = material,
                    isExcludeDetails = isExcludeDetails,
                    thumbnailSize = thumbnailSize
                )
            }
            async { recipeNameProvider.load() }.await()

            val remoteRecipes = remoteRecipesDeferred.await()

            emit(
                remoteRecipes.map {
                    val localizedName = recipeNameProvider.getName(it.name)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
