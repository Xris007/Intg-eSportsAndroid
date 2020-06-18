package pe.isil.esports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hero(
    val id: Long?,
    val name: String?,
    val roles: String?,
    val poster_path: String?,
    val background_path: String?,
    val intelligence: Double?,
    val agility: Double?,
    val strength: Double?,
    val damage: Double?,
    val move_speed: Double?,
    val armor: Double?
)