package pe.isil.esports.domain.usecase

import kotlinx.coroutines.flow.Flow
import pe.isil.esports.domain.model.God
import pe.isil.esports.domain.repository.GodRepository

class GodUseCase(private val repository: GodRepository) {

    fun getAll(): Flow<List<God>> {
        return repository.getAll()
    }

    fun create(god: God): Flow<God> {
        return repository.create(god)
    }

    fun update(id: Long, god: God): Flow<God> {
        return repository.update(id, god)
    }

    fun delete(id: Long): Flow<String> {
        return repository.delete(id)
    }

    fun findById(id: Long): Flow<God> {
        return repository.findById(id)
    }

}