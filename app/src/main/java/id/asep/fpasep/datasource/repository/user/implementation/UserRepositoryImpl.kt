package id.asep.fpasep.datasource.repository.user.implementation

import com.haroldadmin.cnradapter.NetworkResponse
import id.asep.fpasep.datasource.local.models.User
import id.asep.fpasep.datasource.local.service.UserService
import id.asep.fpasep.datasource.remote.models.RestResponse
import id.asep.fpasep.datasource.remote.models.user.AuthResponse
import id.asep.fpasep.datasource.remote.models.user.UserLoginRequest
import id.asep.fpasep.datasource.remote.models.user.UserRegisterRequest
import id.asep.fpasep.datasource.remote.service.FoodAPIServices
import id.asep.fpasep.datasource.repository.user.UserRepository
import id.asep.fpasep.datasource.repository.user.auth.UserAuthentication
import id.asep.fpasep.ui.authentication.domain.UserDomain
import id.asep.fpasep.ui.authentication.viewmodel.state.RegisterState
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
    override fun login(
        userCredential: UserLoginRequest,
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

    override suspend fun register(userDomain: UserDomain): RegisterState {
        val dataRequest = UserRegisterRequest(
            name = userDomain.name,
            password = userDomain.password,
            email = userDomain.email,
            houseNumber = userDomain.houseNumber,
            phoneNumber = userDomain.phoneNumber,
            city = userDomain.city,
            address = userDomain.address,
            passwordConfirmation = userDomain.password
        )
        val response = plainRetrofit.create(FoodAPIServices::class.java).userRegister(dataRequest)
        if (response is NetworkResponse.Success) {
            val accessToken = response.body.data?.accessToken
            val dataUser = response.body.data?.user
            if (accessToken != null && accessToken.isNotBlank() && dataUser != null) {
                sharedPreferences.putString(UserAuthentication.PREF_TOKEN_KEY, accessToken)
                val user = User(
                    dataUser.id,
                    dataUser.name,
                    dataUser.email,
                    dataUser.address ?: "",
                    dataUser.houseNumber ?: "",
                    dataUser.phoneNumber ?: "",
                    dataUser.city ?: "",
                    User.Roles(dataUser.roles),
                    dataUser.profilePhotoUrl
                )
                userService.insert(user)
                return RegisterState.RegisterSuccess
            } else {
                return RegisterState.RegisterError("Register Failed")
            }
        } else {
            return RegisterState.RegisterError("Register Failed")
        }
    }

    override suspend fun logout() {
        sharedPreferences.clearAll()
    }

    override fun isLoggedIn(): Boolean {
        val accessToken = sharedPreferences.getString(UserAuthentication.PREF_TOKEN_KEY, "")
        return accessToken.isNotBlank()
    }
}