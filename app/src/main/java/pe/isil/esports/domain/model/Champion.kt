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
    val health: String?,
    val regeneration: String?,
    val move_speed: String?,
    val attack_damage: String?,
    val attack_speed: String?,
    val range: String?,
    val armor: String?,
    val resistance: String?
)