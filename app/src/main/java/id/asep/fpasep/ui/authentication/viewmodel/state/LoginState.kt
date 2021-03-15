package id.asep.fpasep.ui.authentication.viewmodel.state

sealed class LoginState
class LoginError(val errorMessage: String) : LoginState()
object LoginSuccess : LoginState()