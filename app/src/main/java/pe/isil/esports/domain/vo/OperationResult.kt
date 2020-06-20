package pe.isil.esports.domain.vo

sealed class OperationResult<out T> {
    data class Data<T>(val data: T) : OperationResult<T>()
    data class Error(val message: String) : OperationResult<Nothing>()
}