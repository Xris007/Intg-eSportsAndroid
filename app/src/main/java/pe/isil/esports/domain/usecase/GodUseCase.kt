package pe.isil.esports.domain.usecase

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.domain.model.God
import pe.isil.esports.domain.repository.GodRepository
import pe.isil.esports.domain.vo.OperationResult

class GodUseCase(private val repository: GodRepository) {

    fun getGuardians(): Flow<OperationResult<List<God>>>{
        return repository.getGuardians()
    }

    fun getWarriors(): Flow<OperationResult<List<God>>>{
        return repository.getWarriors()
    }

    fun getHunters(): Flow<OperationResult<List<God>>>{
        return repository.getHunters()
    }

    fun getMages(): Flow<OperationResult<List<God>>>{
        return repository.getMages()
    }

    fun getAssassins(): Flow<OperationResult<List<God>>>{
        return repository.getAssassins()
    }

    fun create(god: God): Flow<OperationResult<God>> {
        return repository.create(god)
    }

    fun update(id: Long, god: God): Flow<OperationResult<God>> {
        return repository.update(id, god)
    }

    fun delete(id: Long): Flow<OperationResult<String>> {
        return repository.delete(id)
    }

    fun findById(id: Long): Flow<OperationResult<God>> {
        return repository.findById(id)
    }

}