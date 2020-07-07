package pe.isil.esports.di.module

import android.content.Context
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pe.isil.esports.data.repository.ChampionRepositoryImpl
import pe.isil.esports.data.repository.GodRepositoryImpl
import pe.isil.esports.data.repository.HeroRepositoryImpl
import pe.isil.esports.data.repository.LoginRepositoryImpl
import pe.isil.esports.data.source.remote.service.ChampionService
import pe.isil.esports.data.source.remote.service.GodService
import pe.isil.esports.data.source.remote.service.HeroService
import pe.isil.esports.data.source.remote.service.LoginService
import pe.isil.esports.domain.repository.ChampionRepository
import pe.isil.esports.domain.repository.GodRepository
import pe.isil.esports.domain.repository.HeroRepository
import pe.isil.esports.domain.repository.LoginRepository
import pe.isil.esports.domain.usecase.ChampionUseCase
import pe.isil.esports.domain.usecase.GodUseCase
import pe.isil.esports.domain.usecase.HeroUseCase
import pe.isil.esports.domain.usecase.LoginUseCase
import pe.isil.esports.presentation.MainViewModel
import pe.isil.esports.presentation.champion.ChampionViewModel
import pe.isil.esports.presentation.god.GodViewModel
import pe.isil.esports.presentation.hero.HeroViewModel

@ExperimentalCoroutinesApi
val championModule = module {

    single { createChampionRepository(get()) }

    single { createChampionUseCase(get()) }

    viewModel { ChampionViewModel(get()) }
}

@ExperimentalCoroutinesApi
val godModule = module {

    single { createGodRepository(get()) }

    single { createGodUseCase(get()) }

    viewModel { GodViewModel(get()) }
}


@ExperimentalCoroutinesApi
val heroModule = module {

    single { createHeroRepository(get()) }

    single { createHeroUseCase(get()) }

    viewModel { HeroViewModel(get()) }
}

val loginModule = module {

    single { createLoginRepository(get(),androidContext()) }

    single { createLoginUseCase(get()) }

    viewModel { MainViewModel(get()) }
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

fun createLoginRepository(service: LoginService, context: Context): LoginRepository {
    return LoginRepositoryImpl(service, context)
}

fun createLoginUseCase(repository: LoginRepository): LoginUseCase {
    return LoginUseCase(repository)
}