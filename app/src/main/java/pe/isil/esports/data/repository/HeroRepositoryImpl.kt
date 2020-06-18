package pe.isil.esports.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import pe.isil.esports.data.source.remote.HeroService
import pe.isil.esports.domain.exception.EmptyListException
import pe.isil.esports.domain.exception.ServiceException
import pe.isil.esports.domain.model.Hero
import pe.isil.esports.domain.repository.HeroRepository

class HeroRepositoryImpl(private val service: HeroService) : HeroRepository {

    override fun getAll(): Flow<List<Hero>> {
        return flow {
            emit(service.getAll())
        }.map {
            val heroes = it.body()
            if (it.isSuccessful) {
                if (heroes.isNullOrEmpty()) throw EmptyListException() else heroes
            } else {
                throw ServiceException(it.message())
            }
        }.catch { e ->
            Exception(e)
        }.flowOn(Dispatchers.IO)
    }

    override fun create(hero: Hero): Flow<Hero> {
        return flow {
            emit(service.create(hero))
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

    override fun update(id: Long, hero: Hero): Flow<Hero> {
        return flow {
            emit(service.update(id, hero))
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

    override fun findById(id: Long): Flow<Hero> {
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