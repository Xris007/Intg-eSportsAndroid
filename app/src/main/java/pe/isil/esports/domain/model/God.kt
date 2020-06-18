package pe.isil.esports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class God(
    val id: Long?,
    val name: String?,
    val pantheon: String?,
    val title: String?,
    val class_god: String?,
    val attribute: String?,
    val type: String?,
    val poster_path: String?,
    val background_path: String?,
    val health: Double?,
    val energy: Double?,
    val speed: Double?,
    val range: Double?,
    val attack: Double?,
    val magic_protection: Double?,
    val physical_protection: Double?
)