package id.asep.fpasep.datasource.repository.user.implementation

import id.asep.fpasep.datasource.local.service.UserService
import id.asep.fpasep.datasource.remote.models.RestResponse
import id.asep.fpasep.datasource.remote.models.user.AuthResponse
import id.asep.fpasep.datasource.remote.service.FoodAPIServices
import id.asep.fpasep.datasource.repository.user.UserRepository
import id.asep.fpasep.datasource.repository.user.auth.UserAuthentication
import id.asep.fpasep.ui.authentication.domain.UserDomain
import id.asep.fpasep.utils.exception.AuthFailedException
import id.asep.fpasep.utils.exception.ExceptionHandler
import id.asep.fpasep.utils.helper.ApiCallBackWrapper
import id.asep.fpasep.utils.helper.FoodSharedPreferences
import retrofit2.Retrofit
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val plainRetrofit: Retrofit,
    private val sharedPreferences: FoodSharedPreferences,
    private val userService: UserService
) : UserRepository {
    override suspend fun login(
        userCredential: UserAuthentication.AuthCredential,
        authCallBack: UserAuthentication.AuthCallBack
    ) {
        plainRetrofit.create(FoodAPIServices::class.java)
            .userLogin(userCredential)
            .enqueue(object : ApiCallBackWrapper<RestResponse<AuthResponse>>() {
                override fun onSuccess(response: RestResponse<AuthResponse>?) {
                    if (response?.data == null) {
                        val metaMessage = response?.meta?.message
                        val codeError = response?.meta?.code
                        if (!metaMessage.isNullOrBlank() && codeError != null) {
                            onFailure(ExceptionHandler(codeError, metaMessage))
                        } else {
                            onFailure(
                                ExceptionHandler(
                                    UserAuthentication.UNAUTHORIZED_STATUS_CODE,
                                    "UnAuthorized"
                                )
                            )
                        }
                    } else {
                        val accessToken = response.data.accessToken
                        sharedPreferences.putString(
                            UserAuthentication.PREF_TOKEN_KEY,
                            accessToken ?: ""
                        )
                        authCallBack.onSuccess()
                    }
                }

                override fun onFailure(message: ExceptionHandler) {
                    authCallBack.onFailure(AuthFailedException((message)))
                }
            })
    }

    override suspend fun register(userDomain: UserDomain) {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun isLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }
}