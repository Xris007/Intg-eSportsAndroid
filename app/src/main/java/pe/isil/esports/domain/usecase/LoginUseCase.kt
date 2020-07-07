package pe.isil.esports.domain.usecase

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.data.source.remote.LoginResponse
import pe.isil.esports.domain.repository.LoginRepository
import pe.isil.esports.domain.vo.OperationResult

class LoginUseCase(private val repository: LoginRepository) {

    fun login(username: String, password: String): Flow<OperationResult<LoginResponse>> {
        return repository.login(username, password)
    }
}