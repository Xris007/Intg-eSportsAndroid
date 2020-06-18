package pe.isil.esports.domain.repository

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.domain.model.Hero
import pe.isil.esports.domain.vo.OperationResult

interface HeroRepository {
    fun getAll(): Flow<OperationResult<List<Hero>>>
    fun create(hero: Hero): Flow<OperationResult<Hero>>
    fun update(id: Long, hero: Hero): Flow<OperationResult<Hero>>
    fun delete(id: Long): Flow<OperationResult<String>>
    fun findById(id: Long): Flow<OperationResult<Hero>>
}