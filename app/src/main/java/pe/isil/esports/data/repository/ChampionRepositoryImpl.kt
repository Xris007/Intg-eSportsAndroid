package pe.isil.esports.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import pe.isil.esports.data.source.remote.service.ChampionService
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
                    champion.rol == "Assassin" || champion.rol == "Skirmisher"
                }.sortedBy { champion -> champion.id })
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
                    champion.rol == "Juggernaut" || champion.rol == "Diver"
                }.sortedBy { champion -> champion.id })
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
                    champion.rol == "Burst" || champion.rol == "Battlemage" || champion.rol == "Specialist" || champion.rol == "Artillery"
                }.sortedBy { champion -> champion.id })
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
                }.sortedBy { champion -> champion.id })
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
                    champion.rol == "Catcher" || champion.rol == "Enchanter"
                }.sortedBy { champion -> champion.id })
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
                    champion.rol == "Vanguard" || champion.rol == "Warden"
                }.sortedBy { champion -> champion.id })
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