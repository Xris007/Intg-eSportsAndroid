package pe.isil.esports.data.source.remote.service

import pe.isil.esports.domain.model.Champion
import retrofit2.Response
import retrofit2.http.*

interface ChampionService {

    @GET("public/lol")
    suspend fun getAll(): Response<List<Champion>>

    @POST("private/lol")
    suspend fun create(@Body champion: Champion): Response<Champion>

    @PUT("private/lol/{id}")
    suspend fun update(@Path("id") id: Long, @Body champion: Champion): Response<Champion>

    @DELETE("private/lol/{id}")
    suspend fun delete(@Path("id") id: Long): Response<String>

    @GET("public/lol/{id}")
    suspend fun findById(@Path("id") id: Long): Response<Champion>

}