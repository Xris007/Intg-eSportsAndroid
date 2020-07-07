package pe.isil.esports.data.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import pe.isil.esports.data.source.preferences.SessionManager
import pe.isil.esports.data.source.remote.LoginRequest
import pe.isil.esports.data.source.remote.LoginResponse
import pe.isil.esports.data.source.remote.service.LoginService
import pe.isil.esports.domain.repository.LoginRepository
import pe.isil.esports.domain.vo.OperationResult

class LoginRepositoryImpl(private val service: LoginService, private val context: Context) :
    LoginRepository {

    override fun login(username: String, password: String): Flow<OperationResult<LoginResponse>> {
        return flow {
            emit(service.login(LoginRequest(username, password)))
        }.map {
            if (it.isSuccessful) {
                SessionManager.saveToken(context, it.body()!!.token!!)
                OperationResult.Data(it.body()!!)
            } else {
                OperationResult.Error(it.message())
            }
        }.catch { e ->
            OperationResult.Error("$e")
        }.flowOn(Dispatchers.IO)
    }
}