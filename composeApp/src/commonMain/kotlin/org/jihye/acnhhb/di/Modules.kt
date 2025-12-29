package org.jihye.acnhhb.di

import org.jihye.acnhhb.data.createDataStore
import org.jihye.acnhhb.data.remote.NookipediaNetwork
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.data.repository.BugRepositoryImpl
import org.jihye.acnhhb.data.repository.FishRepositoryImpl
import org.jihye.acnhhb.data.repository.FlowerBreedingRepositoryImpl
import org.jihye.acnhhb.data.repository.SeaCreatureRepositoryImpl
import org.jihye.acnhhb.data.repository.SettingsRepositoryImpl
import org.jihye.acnhhb.data.repository.VillagerRepositoryImpl
import org.jihye.acnhhb.domain.repository.BugRepository
import org.jihye.acnhhb.domain.repository.FishRepository
import org.jihye.acnhhb.domain.repository.FlowerBreedingRepository
import org.jihye.acnhhb.domain.repository.SeaCreatureRepository
import org.jihye.acnhhb.domain.repository.SettingsRepository
import org.jihye.acnhhb.domain.repository.VillagerRepository
import org.jihye.acnhhb.ui.bug.BugViewModel
import org.jihye.acnhhb.ui.fish.FishViewModel
import org.jihye.acnhhb.ui.flowerbreeding.FlowerBreedingViewModel
import org.jihye.acnhhb.ui.sea.SeaCreatureViewModel
import org.jihye.acnhhb.ui.settings.SettingsViewModel
import org.jihye.acnhhb.ui.villager.VillagerViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val dataModule = module {
    singleOf(::NookipediaNetwork)
    singleOf(::RemoteDataSource)
    single { createDataStore() }
}

val repositoryModule = module {
    singleOf(::VillagerRepositoryImpl) { bind<VillagerRepository>() }
    singleOf(::FlowerBreedingRepositoryImpl) { bind<FlowerBreedingRepository>() }
    singleOf(::SettingsRepositoryImpl) { bind<SettingsRepository>() }
    singleOf(::FishRepositoryImpl) { bind<FishRepository>() }
    singleOf(::BugRepositoryImpl) { bind<BugRepository>() }
    singleOf(::SeaCreatureRepositoryImpl) { bind<SeaCreatureRepository>() }
}

val viewModelModule = module {
    viewModelOf(::FlowerBreedingViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::VillagerViewModel)
    viewModelOf(::FishViewModel)
    viewModelOf(::BugViewModel)
    viewModelOf(::SeaCreatureViewModel)
}

val appModules = listOf(dataModule, repositoryModule, viewModelModule)

fun startDependencyInjection(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(appModules)
}
