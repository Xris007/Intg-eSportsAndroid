package pe.isil.esports.domain.repository

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.data.source.remote.LoginResponse
import pe.isil.esports.domain.vo.OperationResult

interface LoginRepository {
    fun login(username: String, password: String): Flow<OperationResult<LoginResponse>>
}