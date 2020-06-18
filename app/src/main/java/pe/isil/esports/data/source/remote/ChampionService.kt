package pe.isil.esports.data.source.remote

import pe.isil.esports.domain.model.Champion
import retrofit2.Response
import retrofit2.http.*

interface ChampionService {

    @GET("lol")
    suspend fun getAll(): Response<List<Champion>>

    @POST("lol")
    suspend fun create(@Body champion: Champion): Response<Champion>

    @PUT("lol/{id}")
    suspend fun update(@Path("id") id: Long, @Body champion: Champion): Response<Champion>

    @DELETE("lol/{id}")
    suspend fun delete(@Path("id") id: Long): Response<String>

    @GET("lol/{id}")
    suspend fun findById(@Path("id") id: Long): Response<Champion>

}