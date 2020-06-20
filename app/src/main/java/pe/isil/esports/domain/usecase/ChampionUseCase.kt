package pe.isil.esports.domain.usecase

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.domain.model.Champion
import pe.isil.esports.domain.repository.ChampionRepository
import pe.isil.esports.domain.vo.OperationResult

class ChampionUseCase(private val repository: ChampionRepository) {

    fun getAssassins(): Flow<OperationResult<List<Champion>>> {
        return repository.getAssassins()
    }

    fun getFighters(): Flow<OperationResult<List<Champion>>> {
        return repository.getFighters()
    }

    fun getMages(): Flow<OperationResult<List<Champion>>> {
        return repository.getMages()
    }

    fun getMarksmen(): Flow<OperationResult<List<Champion>>> {
        return repository.getMarksmen()
    }

    fun getSupports(): Flow<OperationResult<List<Champion>>> {
        return repository.getSupports()
    }

    fun getTanks(): Flow<OperationResult<List<Champion>>> {
        return repository.getTanks()
    }

    fun create(champion: Champion): Flow<OperationResult<Champion>> {
        return repository.create(champion)
    }

    fun update(id: Long, champion: Champion): Flow<OperationResult<Champion>> {
        return repository.update(id, champion)
    }

    fun delete(id: Long): Flow<OperationResult<String>> {
        return repository.delete(id)
    }

    fun findById(id: Long): Flow<OperationResult<Champion>> {
        return repository.findById(id)
    }

}