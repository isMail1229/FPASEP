package id.asep.fpasep.datasource.remote.service

import com.haroldadmin.cnradapter.NetworkResponse
import id.asep.fpasep.datasource.remote.models.RestErrorModel
import id.asep.fpasep.datasource.remote.models.RestResponse
import id.asep.fpasep.datasource.remote.models.user.AuthResponse
import id.asep.fpasep.datasource.remote.models.user.UserLoginRequest
import id.asep.fpasep.datasource.remote.models.user.UserRegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FoodAPIServices {

    @POST("/api/login")
    fun userLogin(
        @Body restRequestLogin: UserLoginRequest
    ): Call<RestResponse<AuthResponse>>

    @POST("/api/register")
    suspend fun userRegister(
        @Body restRequsetRegister: UserRegisterRequest
    ): NetworkResponse<RestResponse<AuthResponse>, RestErrorModel>

    @POST("/api/logout")
    suspend fun userLogout(): NetworkResponse<RestResponse<Boolean>, RestErrorModel>
}