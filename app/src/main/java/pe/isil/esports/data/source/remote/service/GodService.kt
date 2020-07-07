package pe.isil.esports.data.source.remote.service

import pe.isil.esports.domain.model.God
import retrofit2.Response
import retrofit2.http.*

interface GodService {

    @GET("public/smite")
    suspend fun getAll(): Response<List<God>>

    @POST("private/smite")
    suspend fun create(@Body god: God): Response<God>

    @PUT("private/smite/{id}")
    suspend fun update(@Path("id") id: Long, @Body god: God): Response<God>

    @DELETE("private/smite/{id}")
    suspend fun delete(@Path("id") id: Long): Response<String>

    @GET("public/smite/{id}")
    suspend fun findById(@Path("id") id: Long): Response<God>

}