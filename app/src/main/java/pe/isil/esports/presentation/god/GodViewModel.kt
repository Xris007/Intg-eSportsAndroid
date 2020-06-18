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

    private val listViewState = ViewState<List<God>>()

    fun getAll(): LiveData<ViewState<List<God>>> {
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