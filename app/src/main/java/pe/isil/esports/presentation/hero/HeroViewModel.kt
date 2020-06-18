package pe.isil.esports.presentation.hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import pe.isil.esports.domain.exception.EmptyListException
import pe.isil.esports.domain.exception.ServiceException
import pe.isil.esports.domain.model.Hero
import pe.isil.esports.domain.usecase.HeroUseCase

class HeroViewModel(private val useCase: HeroUseCase) : ViewModel() {

    private val _heroes = MutableLiveData<List<Hero>>()
    val heroes: LiveData<List<Hero>> = _heroes

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun getAll(): LiveData<List<Hero>> {
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