package pe.isil.esports.domain.vo

data class ViewState<T>(
    val data: T? = null,
    val error: String? = null
)