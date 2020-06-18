package pe.isil.esports.di.module

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pe.isil.esports.data.repository.ChampionRepositoryImpl
import pe.isil.esports.data.repository.GodRepositoryImpl
import pe.isil.esports.data.repository.HeroRepositoryImpl
import pe.isil.esports.data.source.remote.ChampionService
import pe.isil.esports.data.source.remote.GodService
import pe.isil.esports.data.source.remote.HeroService
import pe.isil.esports.domain.repository.ChampionRepository
import pe.isil.esports.domain.repository.GodRepository
import pe.isil.esports.domain.repository.HeroRepository
import pe.isil.esports.domain.usecase.ChampionUseCase
import pe.isil.esports.domain.usecase.GodUseCase
import pe.isil.esports.domain.usecase.HeroUseCase
import pe.isil.esports.presentation.hero.HeroViewModel
import pe.isil.esports.presentation.champion.ChampionViewModel
import pe.isil.esports.presentation.god.GodViewModel

val championModule = module {

    single { createChampionRepository(get()) }

    single { createChampionUseCase(get()) }

    viewModel { ChampionViewModel(get()) }
}

val godModule = module {

    single { createGodRepository(get()) }

    single { createGodUseCase(get()) }

    viewModel { GodViewModel(get()) }
}


val heroModule = module {

    single { createHeroRepository(get()) }

    single { createHeroUseCase(get()) }

    viewModel { HeroViewModel(get()) }
}

fun createChampionRepository(service: ChampionService): ChampionRepository {
    return ChampionRepositoryImpl(service)
}

fun createChampionUseCase(repository: ChampionRepository): ChampionUseCase {
    return ChampionUseCase(repository)
}

fun createGodRepository(service: GodService): GodRepository {
    return GodRepositoryImpl(service)
}

fun createGodUseCase(repository: GodRepository): GodUseCase {
    return GodUseCase(repository)
}

fun createHeroRepository(service: HeroService): HeroRepository {
    return HeroRepositoryImpl(service)
}

fun createHeroUseCase(repository: HeroRepository): HeroUseCase {
    return HeroUseCase(repository)
}