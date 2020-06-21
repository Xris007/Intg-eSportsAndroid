package pe.isil.esports.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import pe.isil.esports.data.source.remote.HeroService
import pe.isil.esports.domain.model.Hero
import pe.isil.esports.domain.repository.HeroRepository
import pe.isil.esports.domain.vo.OperationResult

class HeroRepositoryImpl(private val service: HeroService) : HeroRepository {

    override fun getStrength(): Flow<OperationResult<List<Hero>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { hero ->
                    hero.attribute == "Strength"
                })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun getAgility(): Flow<OperationResult<List<Hero>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { hero ->
                    hero.attribute == "Agility"
                })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun getIntelligence(): Flow<OperationResult<List<Hero>>> {
        return flow {
            emit(service.getAll())
        }.map {
            if (it.isSuccessful) {
                OperationResult.Data(it.body()!!.filter { hero ->
                    hero.attribute == "Intelligence"
                })
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun create(hero: Hero): Flow<OperationResult<Hero>> {
        return flow {
            emit(service.create(hero))
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

    override fun update(id: Long, hero: Hero): Flow<OperationResult<Hero>> {
        return flow {
            emit(service.update(id, hero))
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

    override fun findById(id: Long): Flow<OperationResult<Hero>> {
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