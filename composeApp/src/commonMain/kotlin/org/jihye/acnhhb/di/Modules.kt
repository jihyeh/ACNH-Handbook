package org.jihye.acnhhb.di

import org.jihye.acnhhb.data.remote.NookipediaNetwork
import org.jihye.acnhhb.data.remote.RemoteDataSource
import org.jihye.acnhhb.data.repository.VillagerRepositoryImpl
import org.jihye.acnhhb.domain.repository.VillagerRepository
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    singleOf(::NookipediaNetwork)
    singleOf(::RemoteDataSource)
    singleOf(::VillagerRepositoryImpl) { bind<VillagerRepository>() }
}

fun startDependencyInjection(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(appModule)
}