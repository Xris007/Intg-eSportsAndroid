package pe.isil.esports.data.source.remote.service

import pe.isil.esports.domain.model.Login
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("authenticate")
    suspend fun login(@Body request: Login): Response<Unit>
}