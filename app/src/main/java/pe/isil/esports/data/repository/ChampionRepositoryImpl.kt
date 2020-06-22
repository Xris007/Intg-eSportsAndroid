package pe.isil.esports.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import pe.isil.esports.data.source.remote.ChampionService
import pe.isil.esports.domain.model.Champion
import pe.isil.esports.domain.repository.ChampionRepository
import pe.isil.esports.domain.vo.OperationResult

class ChampionRepositoryImpl(private val service: ChampionService) : ChampionRepository {

    override fun getAssassins(): Flow<OperationResult<List<Champion>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { champion ->
                    champion.rol == "Assassin"
                })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun getFighters(): Flow<OperationResult<List<Champion>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { champion ->
                    champion.rol == "Fighter"
                })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun getMages(): Flow<OperationResult<List<Champion>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { champion ->
                    champion.rol == "Mage"
                })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun getMarksmen(): Flow<OperationResult<List<Champion>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { champion ->
                    champion.rol == "Marksman"
                })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun getSupports(): Flow<OperationResult<List<Champion>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { champion ->
                    champion.rol == "Support"
                })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun getTanks(): Flow<OperationResult<List<Champion>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { champion ->
                    champion.rol == "Tank"
                })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun create(champion: Champion): Flow<OperationResult<Champion>> {
        return flow {
            emit(service.create(champion))
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

    override fun update(id: Long, champion: Champion): Flow<OperationResult<Champion>> {
        return flow {
            emit(service.update(id, champion))
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

    override fun findById(id: Long): Flow<OperationResult<Champion>> {
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