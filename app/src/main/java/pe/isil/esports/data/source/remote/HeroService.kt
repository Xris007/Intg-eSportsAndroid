package pe.isil.esports.data.source.remote

import pe.isil.esports.domain.model.Hero
import retrofit2.Response
import retrofit2.http.*

interface HeroService {

    @GET("dota")
    suspend fun getAll(): Response<List<Hero>>

    @POST("dota")
    suspend fun create(@Body hero: Hero): Response<Hero>

    @PUT("dota/{id}")
    suspend fun update(@Path("id") id: Long, @Body hero: Hero): Response<Hero>

    @DELETE("dota/{id}")
    suspend fun delete(@Path("id") id: Long): Response<String>

    @GET("dota/{id}")
    suspend fun findById(@Path("id") id: Long): Response<Hero>

}