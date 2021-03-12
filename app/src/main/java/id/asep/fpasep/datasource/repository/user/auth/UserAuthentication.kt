package id.asep.fpasep.datasource.repository.user.auth

import id.asep.fpasep.utils.exception.AuthFailedException

interface UserAuthentication {

    interface AuthCallBack {
        fun onSuccess()
        fun onFailure(e: AuthFailedException)
    }

    interface AuthCredential

    companion object {
        const val AUTHENTICATION_HEADER = "Authorization"
        const val PREF_TOKEN_KEY = "USER_TOKEN_KEY"
        const val UNAUTHORIZED_STATUS_CODE = 401
    }
}
