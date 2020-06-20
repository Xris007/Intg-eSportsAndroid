package pe.isil.esports.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pe.isil.esports.data.source.remote.ChampionService
import pe.isil.esports.domain.model.Champion
import pe.isil.esports.domain.repository.ChampionRepository
import pe.isil.esports.domain.vo.OperationResult

class ChampionRepositoryImpl(private val service: ChampionService) : ChampionRepository {

    override fun getAssassins(): Flow<OperationResult<List<Champion>>> {
        return flow {
            try {
                val response = service.getAll()
                if (response.isSuccessful && response.body() != null) {
                    emit(
                        OperationResult.Data(
                            response.body()!!
                                .filter { it.primary_rol == "Assassin" || it.secondary_rol == "Assassin" })
                    )
                } else {
                    emit(OperationResult.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(OperationResult.Error("${e.message}"))
            }
        }.catch {
            emit(OperationResult.Error("${it.message}"))
        }.flowOn(Dispatchers.IO)
    }

    override fun getFighters(): Flow<OperationResult<List<Champion>>> {
        return flow {
            try {
                val response = service.getAll()
                if (response.isSuccessful && response.body() != null) {
                    emit(
                        OperationResult.Data(
                            response.body()!!
                                .filter { it.primary_rol == "Fighter" || it.secondary_rol == "Fighter" })
                    )
                } else {
                    emit(OperationResult.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(OperationResult.Error("${e.message}"))
            }
        }.catch {
            emit(OperationResult.Error("${it.message}"))
        }.flowOn(Dispatchers.IO)
    }

    override fun getMages(): Flow<OperationResult<List<Champion>>> {
        return flow {
            try {
                val response = service.getAll()
                if (response.isSuccessful && response.body() != null) {
                    emit(
                        OperationResult.Data(
                            response.body()!!
                                .filter { it.primary_rol == "Mage" || it.secondary_rol == "Mage" })
                    )
                } else {
                    emit(OperationResult.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(OperationResult.Error("${e.message}"))
            }
        }.catch {
            emit(OperationResult.Error("${it.message}"))
        }.flowOn(Dispatchers.IO)
    }

    override fun getMarksmen(): Flow<OperationResult<List<Champion>>> {
        return flow {
            try {
                val response = service.getAll()
                if (response.isSuccessful && response.body() != null) {
                    emit(
                        OperationResult.Data(
                            response.body()!!
                                .filter { it.primary_rol == "Marksman" || it.secondary_rol == "Marksman" })
                    )
                } else {
                    emit(OperationResult.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(OperationResult.Error("${e.message}"))
            }
        }.catch {
            emit(OperationResult.Error("${it.message}"))
        }.flowOn(Dispatchers.IO)
    }

    override fun getSupports(): Flow<OperationResult<List<Champion>>> {
        return flow {
            try {
                val response = service.getAll()
                if (response.isSuccessful && response.body() != null) {
                    emit(
                        OperationResult.Data(
                            response.body()!!
                                .filter { it.primary_rol == "Support" || it.secondary_rol == "Support" })
                    )
                } else {
                    emit(OperationResult.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(OperationResult.Error("${e.message}"))
            }
        }.catch {
            emit(OperationResult.Error("${it.message}"))
        }.flowOn(Dispatchers.IO)
    }

    override fun getTanks(): Flow<OperationResult<List<Champion>>> {
        return flow {
            try {
                val response = service.getAll()
                if (response.isSuccessful && response.body() != null) {
                    emit(
                        OperationResult.Data(
                            response.body()!!
                                .filter { it.primary_rol == "Tank" || it.secondary_rol == "Tank" })
                    )
                } else {
                    emit(OperationResult.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(OperationResult.Error("${e.message}"))
            }
        }.catch {
            emit(OperationResult.Error("${it.message}"))
        }.flowOn(Dispatchers.IO)
    }

    override fun create(champion: Champion): Flow<OperationResult<Champion>> {
        return flow {
            try {
                val response = service.create(champion)
                if (response.isSuccessful && response.body() != null) {
                    emit(OperationResult.Data(response.body()!!))
                } else {
                    emit(OperationResult.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(OperationResult.Error("${e.message}"))
            }
        }.catch {
            emit(OperationResult.Error("${it.message}"))
        }.flowOn(Dispatchers.IO)
    }

    override fun update(id: Long, champion: Champion): Flow<OperationResult<Champion>> {
        return flow {
            try {
                val response = service.update(id, champion)
                if (response.isSuccessful && response.body() != null) {
                    emit(OperationResult.Data(response.body()!!))
                } else {
                    emit(OperationResult.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(OperationResult.Error("${e.message}"))
            }
        }.catch {
            emit(OperationResult.Error("${it.message}"))
        }.flowOn(Dispatchers.IO)
    }

    override fun delete(id: Long): Flow<OperationResult<String>> {
        return flow {
            try {
                val response = service.delete(id)
                if (response.isSuccessful && response.body() != null) {
                    emit(OperationResult.Data(response.body()!!))
                } else {
                    emit(OperationResult.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(OperationResult.Error("${e.message}"))
            }
        }.catch {
            emit(OperationResult.Error("${it.message}"))
        }.flowOn(Dispatchers.IO)
    }

    override fun findById(id: Long): Flow<OperationResult<Champion>> {
        return flow {
            try {
                val response = service.findById(id)
                if (response.isSuccessful && response.body() != null) {
                    emit(OperationResult.Data(response.body()!!))
                } else {
                    emit(OperationResult.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(OperationResult.Error("${e.message}"))
            }
        }.catch {
            emit(OperationResult.Error("${it.message}"))
        }.flowOn(Dispatchers.IO)
    }

}