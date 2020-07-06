package pe.isil.esports.presentation.hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import pe.isil.esports.domain.model.Hero
import pe.isil.esports.domain.usecase.HeroUseCase
import pe.isil.esports.domain.vo.OperationResult
import pe.isil.esports.domain.vo.ViewState

@ExperimentalCoroutinesApi
class HeroViewModel(private val useCase: HeroUseCase) : ViewModel() {

    private val strengthViewState = ViewState<List<Hero>>()
    private val agilityViewState = ViewState<List<Hero>>()
    private val intelligenceViewState = ViewState<List<Hero>>()

    private val heroViewState = ViewState<Hero>()
    private val createViewState = ViewState<Hero>()
    private val updateViewState = ViewState<Hero>()

    fun getStrength(): LiveData<ViewState<List<Hero>>> {
        return useCase.getStrength().map {
            when (it) {
                is OperationResult.Data -> strengthViewState.copy(data = it.data)
                is OperationResult.Error -> strengthViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun getAgility(): LiveData<ViewState<List<Hero>>> {
        return useCase.getAgility().map {
            when (it) {
                is OperationResult.Data -> agilityViewState.copy(data = it.data)
                is OperationResult.Error -> agilityViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun getIntelligence(): LiveData<ViewState<List<Hero>>> {
        return useCase.getIntelligence().map {
            when (it) {
                is OperationResult.Data -> intelligenceViewState.copy(data = it.data)
                is OperationResult.Error -> intelligenceViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun getHero(id: Long): LiveData<ViewState<Hero>> {
        return useCase.findById(id).map {
            when (it) {
                is OperationResult.Data -> heroViewState.copy(data = it.data)
                is OperationResult.Error -> heroViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun createHero(hero: Hero): LiveData<ViewState<Hero>> {
        return useCase.create(hero).map {
            when (it) {
                is OperationResult.Data -> createViewState.copy(data = it.data)
                is OperationResult.Error -> createViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun updateHero(id: Long, hero: Hero): LiveData<ViewState<Hero>> {
        return useCase.update(id, hero).map {
            when (it) {
                is OperationResult.Data -> updateViewState.copy(data = it.data)
                is OperationResult.Error -> updateViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

}