package id.asep.fpasep.datasource.remote.service

import com.haroldadmin.cnradapter.NetworkResponse
import id.asep.fpasep.datasource.remote.models.RestErrorModel
import id.asep.fpasep.datasource.remote.models.RestResponse
import id.asep.fpasep.datasource.remote.models.user.AuthResponse
import id.asep.fpasep.datasource.remote.models.user.UserRegisterRequest
import id.asep.fpasep.datasource.repository.user.auth.UserAuthentication
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FoodAPIServices {

    @POST("/login")
    suspend fun userLogin(
        @Body restRequestLogin: UserAuthentication.AuthCredential
    ): Call<RestResponse<AuthResponse>>

    @POST("/register")
    suspend fun userRegister(
        @Body restRequsetRegister: UserRegisterRequest
    ): NetworkResponse<RestResponse<AuthResponse>, RestErrorModel>

    @POST("/logout")
    suspend fun userLogout(): NetworkResponse<RestResponse<Boolean>, RestErrorModel>
}