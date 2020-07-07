package pe.isil.esports.data.source.remote.service

import pe.isil.esports.data.source.remote.LoginRequest
import pe.isil.esports.data.source.remote.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("authenticate")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}