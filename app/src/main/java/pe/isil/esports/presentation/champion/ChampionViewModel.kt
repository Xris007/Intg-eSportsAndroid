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

    private val listViewState = ViewState<List<Champion>>()

    fun getAll(): LiveData<ViewState<List<Champion>>> {
        return useCase.getAll().map {
            when (it) {
                is OperationResult.Loading -> listViewState.copy(loading = true)
                is OperationResult.Data -> listViewState.copy(
                    loading = false,
                    data = it.data
                )
                is OperationResult.Error -> listViewState.copy(
                    loading = false,
                    error = it.message
                )
            }
        }.asLiveData()
    }

}