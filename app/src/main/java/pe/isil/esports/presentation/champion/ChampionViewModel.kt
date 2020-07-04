package pe.isil.esports.presentation.champion

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import pe.isil.esports.domain.model.Champion
import pe.isil.esports.domain.usecase.ChampionUseCase
import pe.isil.esports.domain.vo.OperationResult
import pe.isil.esports.domain.vo.ViewState

@ExperimentalCoroutinesApi
class ChampionViewModel(private val useCase: ChampionUseCase) : ViewModel() {

    private val assassinsViewState = ViewState<List<Champion>>()
    private val fightersViewState = ViewState<List<Champion>>()
    private val magesViewState = ViewState<List<Champion>>()
    private val marksmenViewState = ViewState<List<Champion>>()
    private val supportsViewState = ViewState<List<Champion>>()
    private val tanksViewState = ViewState<List<Champion>>()

    private val championViewState = ViewState<Champion>()

    fun getAssassins(): LiveData<ViewState<List<Champion>>> {
        return useCase.getAssassins().map {
            when (it) {
                is OperationResult.Data -> assassinsViewState.copy(data = it.data)
                is OperationResult.Error -> assassinsViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun getFighters(): LiveData<ViewState<List<Champion>>> {
        return useCase.getFighters().map {
            when (it) {
                is OperationResult.Data -> fightersViewState.copy(data = it.data)
                is OperationResult.Error -> fightersViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun getMages(): LiveData<ViewState<List<Champion>>> {
        return useCase.getMages().map {
            when (it) {
                is OperationResult.Data -> magesViewState.copy(data = it.data)
                is OperationResult.Error -> magesViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun getMarksmen(): LiveData<ViewState<List<Champion>>> {
        return useCase.getMarksmen().map {
            when (it) {
                is OperationResult.Data -> marksmenViewState.copy(data = it.data)
                is OperationResult.Error -> marksmenViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun getSupports(): LiveData<ViewState<List<Champion>>> {
        return useCase.getSupports().map {
            when (it) {
                is OperationResult.Data -> supportsViewState.copy(data = it.data)
                is OperationResult.Error -> supportsViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun getTanks(): LiveData<ViewState<List<Champion>>> {
        return useCase.getTanks().map {
            when (it) {
                is OperationResult.Data -> tanksViewState.copy(data = it.data)
                is OperationResult.Error -> tanksViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun getChampion(id: Long): LiveData<ViewState<Champion>> {
        return useCase.findById(id).map {
            when (it) {
                is OperationResult.Data -> championViewState.copy(data = it.data)
                is OperationResult.Error -> championViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun createChampion(champion: Champion): LiveData<ViewState<Champion>>{
        return useCase.create(champion).map {
            when(it){
                is OperationResult.Data -> championViewState.copy(data = it.data)
                is OperationResult.Error -> championViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

}