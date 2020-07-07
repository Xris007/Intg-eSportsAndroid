package pe.isil.esports.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import pe.isil.esports.data.source.remote.service.GodService
import pe.isil.esports.domain.model.God
import pe.isil.esports.domain.repository.GodRepository
import pe.isil.esports.domain.vo.OperationResult

class GodRepositoryImpl(private val service: GodService) : GodRepository {

    override fun getGuardians(): Flow<OperationResult<List<God>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { god ->
                    god.type == "Guardian"
                }.sortedBy { god -> god.id })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun getWarriors(): Flow<OperationResult<List<God>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { god ->
                    god.type == "Warrior"
                }.sortedBy { god -> god.id })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun getHunters(): Flow<OperationResult<List<God>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { god ->
                    god.type == "Hunter"
                }.sortedBy { god -> god.id })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun getMages(): Flow<OperationResult<List<God>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { god ->
                    god.type == "Mage"
                }.sortedBy { god -> god.id })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun getAssassins(): Flow<OperationResult<List<God>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { god ->
                    god.type == "Assassin"
                }.sortedBy { god -> god.id })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun create(god: God): Flow<OperationResult<God>> {
        return flow {
            emit(service.create(god))
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!)
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun update(id: Long, god: God): Flow<OperationResult<God>> {
        return flow {
            emit(service.update(id, god))
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!)
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun delete(id: Long): Flow<OperationResult<String>> {
        return flow {
            emit(service.delete(id))
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!)
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun findById(id: Long): Flow<OperationResult<God>> {
        return flow {
            emit(service.findById(id))
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!)
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

}