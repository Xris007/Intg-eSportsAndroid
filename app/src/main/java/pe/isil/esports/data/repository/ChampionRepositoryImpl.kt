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

    override fun getAll(): Flow<OperationResult<List<Champion>>> {
        return flow {
            emit(OperationResult.Loading)
            try {
                val response = service.getAll()
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

    override fun create(champion: Champion): Flow<OperationResult<Champion>> {
        return flow {
            emit(OperationResult.Loading)
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
            emit(OperationResult.Loading)
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
            emit(OperationResult.Loading)
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
            emit(OperationResult.Loading)
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