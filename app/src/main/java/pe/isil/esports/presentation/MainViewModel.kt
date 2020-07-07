package pe.isil.esports.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.map
import pe.isil.esports.data.source.remote.LoginResponse
import pe.isil.esports.domain.usecase.LoginUseCase
import pe.isil.esports.domain.vo.OperationResult
import pe.isil.esports.domain.vo.ViewState

class MainViewModel(private val useCase: LoginUseCase) : ViewModel() {

    private val loginViewState = ViewState<LoginResponse>()

    fun login(username: String, password: String): LiveData<ViewState<LoginResponse>> {
        return useCase.login(username, password).map {
            when (it) {
                is OperationResult.Data -> loginViewState.copy(data = it.data)
                is OperationResult.Error -> loginViewState.copy(error = it.message)
            }
        }.asLiveData()
    }
}