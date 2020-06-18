package pe.isil.esports.presentation.god

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import pe.isil.esports.domain.exception.EmptyListException
import pe.isil.esports.domain.exception.ServiceException
import pe.isil.esports.domain.model.God
import pe.isil.esports.domain.usecase.GodUseCase

class GodViewModel(private val useCase: GodUseCase) : ViewModel() {

    private val _gods = MutableLiveData<List<God>>()
    val gods: LiveData<List<God>> = _gods

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun getAll(): LiveData<List<God>> {
        return useCase.getAll()
            .onStart {
                _isViewLoading.postValue(true)
            }.catch {
                when (it) {
                    is EmptyListException -> _isEmptyList.postValue(true)
                    is ServiceException -> _onMessageError.postValue(it)
                    else -> _onMessageError.postValue(it)
                }
            }.onCompletion {
                _isViewLoading.postValue(false)
            }.asLiveData()
    }

}