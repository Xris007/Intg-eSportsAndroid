package pe.isil.esports.domain.repository

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.domain.model.Hero

interface HeroRepository {
    fun getAll(): Flow<List<Hero>>
    fun create(hero: Hero): Flow<Hero>
    fun update(id: Long, hero: Hero): Flow<Hero>
    fun delete(id: Long): Flow<String>
    fun findById(id: Long): Flow<Hero>
}