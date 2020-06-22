package pe.isil.esports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hero(
    val id: Long?,
    val name: String?,
    val attribute: String?,
    val type: String?,
    val roles: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    val description: String?,
    val strength: String?,
    val agility: String?,
    val intelligence: String?,
    val attack_damage: String?,
    val armor: String?,
    val move_speed: String?,
    val health: String?,
    val hp_regeneration: String?,
    val mana: String?,
    val mp_regeneration: String?
)