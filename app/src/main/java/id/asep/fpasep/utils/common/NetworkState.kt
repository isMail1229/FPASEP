package id.asep.fpasep.utils.common

sealed class NetworkState
object NetworkLoading : NetworkState()
object NetworkIdle : NetworkState()
class NetworkError(val message: String) : NetworkState()
