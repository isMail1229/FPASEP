package id.asep.fpasep.datasource.repository.user

import id.asep.fpasep.datasource.remote.models.user.UserLoginRequest
import id.asep.fpasep.datasource.repository.user.auth.UserAuthentication
import id.asep.fpasep.ui.authentication.domain.UserDomain
import id.asep.fpasep.ui.authentication.viewmodel.state.RegisterState

interface UserRepository {

    fun login(
        userCredential: UserLoginRequest,
        authCallBack: UserAuthentication.AuthCallBack
    )

    suspend fun register(userDomain: UserDomain): RegisterState

    suspend fun logout()

    fun isLoggedIn(): Boolean
}