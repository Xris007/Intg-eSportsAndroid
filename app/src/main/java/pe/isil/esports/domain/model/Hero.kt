package pe.isil.esports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hero(
    val id: Long?,
    val name: String?,
    val roles: String?,
    val type: String?,
    val attribute: String?,
    val poster_path: String?,
    val background_path: String?,
    val strength: Double?,
    val agility: Double?,
    val intelligence: Double?,
    val damage: Double?,
    val move_speed: Double?,
    val armor: Double?
)