package id.asep.fpasep.datasource.repository.user

import id.asep.fpasep.datasource.repository.user.auth.UserAuthentication
import id.asep.fpasep.ui.authentication.domain.UserDomain

interface UserRepository {

    suspend fun login(
        userCredential: UserAuthentication.AuthCredential,
        authCallBack: UserAuthentication.AuthCallBack
    )

    suspend fun register(userDomain: UserDomain)

    suspend fun logout()

    suspend fun isLoggedIn(): Boolean
}