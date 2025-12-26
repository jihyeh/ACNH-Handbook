package org.jihye.acnhhb.di

import org.jihye.acnhhb.data.createDataStore
import org.jihye.acnhhb.data.remote.NookipediaNetwork
import org.jihye.acnhhb.data.repository.FlowerBreedingRepositoryImpl
import org.jihye.acnhhb.data.repository.SettingsRepositoryImpl
import org.jihye.acnhhb.data.repository.VillagerRepositoryImpl
import org.jihye.acnhhb.domain.repository.FlowerBreedingRepository
import org.jihye.acnhhb.domain.repository.SettingsRepository
import org.jihye.acnhhb.domain.repository.VillagerRepository
import org.jihye.acnhhb.ui.flowerbreeding.FlowerBreedingViewModel
import org.jihye.acnhhb.ui.settings.SettingsViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    singleOf(::NookipediaNetwork)
    singleOf(::VillagerRepositoryImpl) { bind<VillagerRepository>() }
    singleOf(::FlowerBreedingRepositoryImpl) { bind<FlowerBreedingRepository>() }
    singleOf(::SettingsRepositoryImpl) { bind<SettingsRepository>() }

    single { createDataStore() }

    viewModelOf(::FlowerBreedingViewModel)
    viewModelOf(::SettingsViewModel)
}

fun startDependencyInjection(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(appModule)
}
