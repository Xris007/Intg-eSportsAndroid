package pe.isil.esports.domain.usecase

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.domain.model.Hero
import pe.isil.esports.domain.repository.HeroRepository
import pe.isil.esports.domain.vo.OperationResult

class HeroUseCase(private val repository: HeroRepository) {

    fun getAll(): Flow<OperationResult<List<Hero>>> {
        return repository.getAll()
    }

    fun create(hero: Hero): Flow<OperationResult<Hero>> {
        return repository.create(hero)
    }

    fun update(id: Long, hero: Hero): Flow<OperationResult<Hero>> {
        return repository.update(id, hero)
    }

    fun delete(id: Long): Flow<OperationResult<String>> {
        return repository.delete(id)
    }

    fun findById(id: Long): Flow<OperationResult<Hero>> {
        return repository.findById(id)
    }

}