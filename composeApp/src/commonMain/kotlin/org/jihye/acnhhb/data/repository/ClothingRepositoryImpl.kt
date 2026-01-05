package org.jihye.acnhhb.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jihye.acnhhb.data.mapper.toDomain
import org.jihye.acnhhb.data.provider.ClothingNameProvider
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.domain.model.Clothing
import org.jihye.acnhhb.domain.repository.ClothingRepository

class ClothingRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val clothingNameProvider: ClothingNameProvider,
) : ClothingRepository {

    override fun getClothing(
        category: String?,
        color: List<String>?,
        style: List<String>?,
        labelTheme: String?,
    ): Flow<List<Clothing>> = flow {
        coroutineScope {
            val remoteClothingDeferred = async {
                remoteDataSource.fetchClothing(
                    category = category,
                    color = color,
                    style = style,
                    labelTheme = labelTheme,
                    isExcludeDetails = null,
                )
            }
            async { clothingNameProvider.load() }.await()

            val remoteClothing = remoteClothingDeferred.await()

            emit(
                remoteClothing.map {
                    val localizedName = clothingNameProvider.getName(it.name)
                    it.toDomain(localizedName)
                }
            )
        }
    }
}
