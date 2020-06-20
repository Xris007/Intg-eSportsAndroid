package pe.isil.esports.presentation.god

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import pe.isil.esports.domain.model.God
import pe.isil.esports.domain.usecase.GodUseCase
import pe.isil.esports.domain.vo.OperationResult
import pe.isil.esports.domain.vo.ViewState

@ExperimentalCoroutinesApi
class GodViewModel(private val useCase: GodUseCase) : ViewModel() {

    private val guardiansViewState = ViewState<List<God>>()
    private val warriorsViewState = ViewState<List<God>>()
    private val huntersViewState = ViewState<List<God>>()
    private val magesViewState = ViewState<List<God>>()
    private val assassinsViewState = ViewState<List<God>>()

    fun getGuardians(): LiveData<ViewState<List<God>>> {
        return useCase.getGuardians().map {
            when (it) {
                is OperationResult.Data -> guardiansViewState.copy(data = it.data)
                is OperationResult.Error -> guardiansViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun getWarriors(): LiveData<ViewState<List<God>>> {
        return useCase.getWarriors()
            .map {
                when (it) {
                    is OperationResult.Data -> warriorsViewState.copy(data = it.data)
                    is OperationResult.Error -> warriorsViewState.copy(error = it.message)
                }
            }.asLiveData()
    }

    fun getHunters(): LiveData<ViewState<List<God>>> {
        return useCase.getHunters().map {
            when (it) {
                is OperationResult.Data -> huntersViewState.copy(data = it.data)
                is OperationResult.Error -> huntersViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun getMages(): LiveData<ViewState<List<God>>> {
        return useCase.getMages().map {
            when (it) {
                is OperationResult.Data -> magesViewState.copy(data = it.data)
                is OperationResult.Error -> magesViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun getAssassins(): LiveData<ViewState<List<God>>> {
        return useCase.getAssassins().map {
            when (it) {
                is OperationResult.Data -> assassinsViewState.copy(data = it.data)
                is OperationResult.Error -> assassinsViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

}