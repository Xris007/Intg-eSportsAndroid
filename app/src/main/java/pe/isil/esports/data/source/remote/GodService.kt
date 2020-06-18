package pe.isil.esports.data.source.remote

import pe.isil.esports.domain.model.God
import retrofit2.Response
import retrofit2.http.*

interface GodService {

    @GET("smite")
    suspend fun getAll(): Response<List<God>>

    @POST("smite")
    suspend fun create(@Body god: God): Response<God>

    @PUT("smite/{id}")
    suspend fun update(@Path("id") id: Long, @Body god: God): Response<God>

    @DELETE("smite/{id}")
    suspend fun delete(@Path("id") id: Long): Response<String>

    @GET("smite/{id}")
    suspend fun findById(@Path("id") id: Long): Response<God>

}