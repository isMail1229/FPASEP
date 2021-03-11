package id.asep.fpasep.datasource.remote.service

import com.haroldadmin.cnradapter.NetworkResponseAdapter
import id.asep.fpasep.datasource.remote.models.RestErrorModel
import id.asep.fpasep.datasource.remote.models.RestResponse
import id.asep.fpasep.datasource.remote.models.user.AuthResponse
import id.asep.fpasep.datasource.remote.models.user.UserLoginRequest
import id.asep.fpasep.datasource.remote.models.user.UserRegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface FoodAPIServices {

    @POST("/login")
    suspend fun userLogin(
        @Body restRequestLogin: UserLoginRequest
    ): NetworkResponseAdapter<RestResponse<AuthResponse>, RestErrorModel>

    @POST("/register")
    suspend fun userRegister(
        @Body restRequsetRegister: UserRegisterRequest
    ): NetworkResponseAdapter<RestResponse<AuthResponse>, RestErrorModel>

    @POST("/logout")
    suspend fun userLogout(): NetworkResponseAdapter<RestResponse<Boolean>, RestErrorModel>
}