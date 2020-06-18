package pe.isil.esports.domain.usecase

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.domain.model.Hero
import pe.isil.esports.domain.repository.HeroRepository

class HeroUseCase(private val repository: HeroRepository) {

    fun getAll(): Flow<List<Hero>> {
        return repository.getAll()
    }

    fun create(hero: Hero): Flow<Hero> {
        return repository.create(hero)
    }

    fun update(id: Long, hero: Hero): Flow<Hero> {
        return repository.update(id, hero)
    }

    fun delete(id: Long): Flow<String> {
        return repository.delete(id)
    }

    fun findById(id: Long): Flow<Hero> {
        return repository.findById(id)
    }

}