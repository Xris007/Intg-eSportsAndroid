package pe.isil.esports.domain.repository

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.domain.model.God

interface GodRepository {
    fun getAll(): Flow<List<God>>
    fun create(god: God): Flow<God>
    fun update(id: Long, god: God): Flow<God>
    fun delete(id: Long): Flow<String>
    fun findById(id: Long): Flow<God>
}