package pe.isil.esports.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.map
import pe.isil.esports.domain.usecase.LoginUseCase
import pe.isil.esports.domain.vo.OperationResult
import pe.isil.esports.domain.vo.ViewState

class LoginViewModel(private val useCase: LoginUseCase) : ViewModel() {

    private val loginViewState = ViewState<Unit>()
    private val activeViewState = ViewState<String?>()
    private val clearViewState = ViewState<Unit>()

    fun login(username: String, password: String): LiveData<ViewState<Unit>> {
        return useCase.login(username, password).map {
            when (it) {
                is OperationResult.Data -> loginViewState.copy(data = it.data)
                is OperationResult.Error -> loginViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun active(): LiveData<ViewState<String?>> {
        return useCase.active().map {
            when (it) {
                is OperationResult.Data -> activeViewState.copy(data = it.data)
                is OperationResult.Error -> activeViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

    fun clear(): LiveData<ViewState<Unit>> {
        return useCase.clear().map {
            when (it) {
                is OperationResult.Data -> clearViewState.copy(data = it.data)
                is OperationResult.Error -> clearViewState.copy(error = it.message)
            }
        }.asLiveData()
    }

}