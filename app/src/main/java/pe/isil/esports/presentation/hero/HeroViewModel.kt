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

    private val listViewState = ViewState<List<Hero>>()

    fun getAll(): LiveData<ViewState<List<Hero>>> {
        return useCase.getAll().map {
            when (it) {
                is OperationResult.Data -> listViewState.copy(data = it.data)
                is OperationResult.Error -> listViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

}