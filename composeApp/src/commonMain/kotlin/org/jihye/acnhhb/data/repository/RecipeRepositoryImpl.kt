package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Recipe
import org.jihye.acnhhb.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : RecipeRepository {
    override fun getRecipes(
        material: String?,
        isExcludeDetails: String?,
        thumbnailSize: Int?,
    ): Flow<List<Recipe>> = flow {
        val remoteRecipes = remoteDataSource.fetchRecipes(
            material = material,
            isExcludeDetails = isExcludeDetails,
            thumbnailSize = thumbnailSize
        )
        emit(remoteRecipes.map { it.toDomain() })
    }
}
