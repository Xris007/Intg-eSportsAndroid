package pe.isil.esports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class God(
    val id: Long?,
    val name: String?,
    val title: String?,
    val type: String?,
    val attributes: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    val description: String?,
    val attack_damage: String?,
    val attack_speed: String?,
    val attack_range: String?,
    val move_speed: String?,
    val armor: String?,
    val magic_resistance: String?,
    val hp_regeneration: String?,
    val mp_regeneration: String?,
    val health: String?,
    val mana: String?
)