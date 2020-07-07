package pe.isil.esports.data.source.remote.service

import pe.isil.esports.domain.model.Hero
import retrofit2.Response
import retrofit2.http.*

interface HeroService {

    @GET("public/dota")
    suspend fun getAll(): Response<List<Hero>>

    @POST("private/dota")
    suspend fun create(@Body hero: Hero): Response<Hero>

    @PUT("private/dota/{id}")
    suspend fun update(@Path("id") id: Long, @Body hero: Hero): Response<Hero>

    @DELETE("private/dota/{id}")
    suspend fun delete(@Path("id") id: Long): Response<String>

    @GET("public/dota/{id}")
    suspend fun findById(@Path("id") id: Long): Response<Hero>

}