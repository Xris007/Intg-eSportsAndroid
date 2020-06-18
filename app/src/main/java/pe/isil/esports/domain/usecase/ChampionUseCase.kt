package pe.isil.esports.domain.usecase

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.domain.model.Champion
import pe.isil.esports.domain.repository.ChampionRepository

class ChampionUseCase(private val repository: ChampionRepository) {

    fun getAll(): Flow<List<Champion>> {
        return repository.getAll()
    }

    fun create(champion: Champion): Flow<Champion> {
        return repository.create(champion)
    }

    fun update(id: Long, champion: Champion): Flow<Champion> {
        return repository.update(id, champion)
    }

    fun delete(id: Long): Flow<String> {
        return repository.delete(id)
    }

    fun findById(id: Long): Flow<Champion> {
        return repository.findById(id)
    }

}