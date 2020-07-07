package pe.isil.esports.data.source.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    val username: String?,
    val password: String?
)