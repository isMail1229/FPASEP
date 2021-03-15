package id.asep.fpasep.ui.authentication.viewmodel.state

sealed class RegisterState {
    class RegisterError(val errorMessage: String) : RegisterState()
    object RegisterSuccess : RegisterState()
    object RegisterLoading : RegisterState()
}