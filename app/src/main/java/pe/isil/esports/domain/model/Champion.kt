package pe.isil.esports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Champion(
    val id: Long?,
    val name: String?,
    val alias: String?,
    val type: String?,
    val attribute: String?,
    val poster_path: String?,
    val background_path: String?,
    val life: Double?,
    val armor: Double?,
    val move_speed: Double?,
    val damage: Double?,
    val damage_range: Double?,
    val speed_attack: Double?
)