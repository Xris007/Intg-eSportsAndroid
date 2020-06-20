package pe.isil.esports.domain.repository

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.domain.model.Champion
import pe.isil.esports.domain.vo.OperationResult

interface ChampionRepository {
    fun getAssassins(): Flow<OperationResult<List<Champion>>>
    fun getFighters(): Flow<OperationResult<List<Champion>>>
    fun getMages(): Flow<OperationResult<List<Champion>>>
    fun getMarksmen(): Flow<OperationResult<List<Champion>>>
    fun getSupports(): Flow<OperationResult<List<Champion>>>
    fun getTanks(): Flow<OperationResult<List<Champion>>>
    fun create(champion: Champion): Flow<OperationResult<Champion>>
    fun update(id: Long, champion: Champion): Flow<OperationResult<Champion>>
    fun delete(id: Long): Flow<OperationResult<String>>
    fun findById(id: Long): Flow<OperationResult<Champion>>
}