package pe.isil.esports.domain.repository

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.domain.model.God
import pe.isil.esports.domain.vo.OperationResult

interface GodRepository {
    fun getAll(): Flow<OperationResult<List<God>>>
    fun create(god: God): Flow<OperationResult<God>>
    fun update(id: Long, god: God): Flow<OperationResult<God>>
    fun delete(id: Long): Flow<OperationResult<String>>
    fun findById(id: Long): Flow<OperationResult<God>>
}