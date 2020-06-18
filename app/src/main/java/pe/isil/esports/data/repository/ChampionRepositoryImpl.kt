package pe.isil.esports.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import pe.isil.esports.data.source.remote.ChampionService
import pe.isil.esports.domain.exception.EmptyListException
import pe.isil.esports.domain.exception.ServiceException
import pe.isil.esports.domain.model.Champion
import pe.isil.esports.domain.repository.ChampionRepository

class ChampionRepositoryImpl(private val service: ChampionService) : ChampionRepository {

    override fun getAll(): Flow<List<Champion>> {
        return flow {
            emit(service.getAll())
        }.map {
            val champions = it.body()
            if (it.isSuccessful) {
                if (champions.isNullOrEmpty()) throw EmptyListException() else champions
            } else {
                throw ServiceException(it.message())
            }
        }.catch { e ->
            Exception(e)
        }.flowOn(Dispatchers.IO)
    }

    override fun create(champion: Champion): Flow<Champion> {
        return flow {
            emit(service.create(champion))
        }.map {
            val response = it.body()!!
            if (it.isSuccessful) {
                response
            } else {
                throw ServiceException(it.message())
            }
        }.catch { e ->
            Exception(e)
        }.flowOn(Dispatchers.IO)
    }

    override fun update(id: Long, champion: Champion): Flow<Champion> {
        return flow {
            emit(service.update(id, champion))
        }.map {
            val response = it.body()!!
            if (it.isSuccessful) {
                response
            } else {
                throw ServiceException(it.message())
            }
        }.catch { e ->
            Exception(e)
        }.flowOn(Dispatchers.IO)
    }

    override fun delete(id: Long): Flow<String> {
        return flow {
            emit(service.delete(id))
        }.map {
            val response = it.body()!!
            if (it.isSuccessful) {
                response
            } else {
                throw ServiceException(it.message())
            }
        }.catch { e ->
            Exception(e)
        }.flowOn(Dispatchers.IO)
    }

    override fun findById(id: Long): Flow<Champion> {
        return flow {
            emit(service.findById(id))
        }.map {
            val response = it.body()!!
            if (it.isSuccessful) {
                response
            } else {
                throw ServiceException(it.message())
            }
        }.catch { e ->
            Exception(e)
        }.flowOn(Dispatchers.IO)
    }

}