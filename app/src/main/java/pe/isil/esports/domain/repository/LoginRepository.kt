package pe.isil.esports.domain.repository

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.domain.vo.OperationResult

interface LoginRepository {
    fun login(username: String, password: String): Flow<OperationResult<Unit>>
    fun active(): Flow<OperationResult<String?>>
    fun clear(): Flow<OperationResult<Unit>>
}