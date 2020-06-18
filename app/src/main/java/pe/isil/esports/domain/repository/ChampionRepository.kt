package pe.isil.esports.domain.repository

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.domain.model.Champion

interface ChampionRepository {
    fun getAll(): Flow<List<Champion>>
    fun create(champion: Champion): Flow<Champion>
    fun update(id: Long, champion: Champion): Flow<Champion>
    fun delete(id: Long): Flow<String>
    fun findById(id: Long): Flow<Champion>
}