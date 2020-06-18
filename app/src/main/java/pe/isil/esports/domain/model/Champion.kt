package pe.isil.esports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Champion(
    val id: Long?,
    val name: String?,
    val title: String?,
    val primary_rol: String?,
    val secondary_rol: String?,
    val poster_path: String?,
    val background_path: String?,
    val health: Double?,
    val regeneration: Double?,
    val move_speed: Double?,
    val attack_damage: Double?,
    val attack_speed: Double?,
    val range: Double?,
    val armor: Double?,
    val resistance: Double?
)