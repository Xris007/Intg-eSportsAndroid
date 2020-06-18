package pe.isil.esports.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import pe.isil.esports.data.source.remote.GodService
import pe.isil.esports.domain.exception.EmptyListException
import pe.isil.esports.domain.exception.ServiceException
import pe.isil.esports.domain.model.God
import pe.isil.esports.domain.repository.GodRepository

class GodRepositoryImpl(private val service: GodService) : GodRepository {

    override fun getAll(): Flow<List<God>> {
        return flow {
            emit(service.getAll())
        }.map {
            val gods = it.body()
            if (it.isSuccessful) {
                if (gods.isNullOrEmpty()) throw EmptyListException() else gods
            } else {
                throw ServiceException(it.message())
            }
        }.catch { e ->
            Exception(e)
        }.flowOn(Dispatchers.IO)
    }

    override fun create(god: God): Flow<God> {
        return flow {
            emit(service.create(god))
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

    override fun update(id: Long, god: God): Flow<God> {
        return flow {
            emit(service.update(id, god))
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

    override fun findById(id: Long): Flow<God> {
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