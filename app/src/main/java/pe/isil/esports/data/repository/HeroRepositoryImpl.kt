package pe.isil.esports.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pe.isil.esports.data.source.remote.HeroService
import pe.isil.esports.domain.model.Hero
import pe.isil.esports.domain.repository.HeroRepository
import pe.isil.esports.domain.vo.OperationResult

class HeroRepositoryImpl(private val service: HeroService) : HeroRepository {


    override fun getStrength(): Flow<OperationResult<List<Hero>>> {
        return flow {
            try {
                val response = service.getAll()
                if (response.isSuccessful && response.body() != null) {
                    emit(OperationResult.Data(response.body()!!.filter { it.attribute == "Strength" }))
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

    override fun getAgility(): Flow<OperationResult<List<Hero>>> {
        return flow {
            try {
                val response = service.getAll()
                if (response.isSuccessful && response.body() != null) {
                    emit(OperationResult.Data(response.body()!!.filter { it.attribute == "Agility" }))
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

    override fun getIntelligence(): Flow<OperationResult<List<Hero>>> {
        return flow {
            try {
                val response = service.getAll()
                if (response.isSuccessful && response.body() != null) {
                    emit(OperationResult.Data(response.body()!!.filter { it.attribute == "Intelligence" }))
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

    override fun create(hero: Hero): Flow<OperationResult<Hero>> {
        return flow {
            try {
                val response = service.create(hero)
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

    override fun update(id: Long, hero: Hero): Flow<OperationResult<Hero>> {
        return flow {
            try {
                val response = service.update(id, hero)
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

    override fun findById(id: Long): Flow<OperationResult<Hero>> {
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