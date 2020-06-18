package pe.isil.esports.domain.vo

data class ViewState<T>(
    val loading: Boolean = false,
    val data: T? = null,
    val error: String? = null
)