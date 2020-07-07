package pe.isil.esports.data.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import pe.isil.esports.data.source.preferences.SessionManager
import pe.isil.esports.data.source.remote.service.LoginService
import pe.isil.esports.domain.model.Login
import pe.isil.esports.domain.repository.LoginRepository
import pe.isil.esports.domain.vo.OperationResult

class LoginRepositoryImpl(private val service: LoginService, private val context: Context) :
    LoginRepository {

    override fun login(username: String, password: String): Flow<OperationResult<Unit>> {
        return flow {
            emit(
                service.login(
                    Login(
                        username,
                        password
                    )
                )
            )
        }.map {
            if (it.isSuccessful) {
                SessionManager.saveToken(context, it.headers()["Authorization"]!!)
                OperationResult.Data(it.body()!!)
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun active(): Flow<OperationResult<String?>> {
        return flow {
            emit(SessionManager.fetchToken(context))
        }.map {
            OperationResult.Data(it)
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }

    override fun clear(): Flow<OperationResult<Unit>> {
        return flow {
            emit(SessionManager.clearToken(context))
        }.map {
            OperationResult.Data(it)
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }
}