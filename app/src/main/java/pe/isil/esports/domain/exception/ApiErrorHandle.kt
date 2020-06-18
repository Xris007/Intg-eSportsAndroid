package pe.isil.esports.domain.exception

class ServiceException(message: String?) : Exception(message)
class EmptyListException : Exception()