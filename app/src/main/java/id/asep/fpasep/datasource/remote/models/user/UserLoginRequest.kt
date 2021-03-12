package id.asep.fpasep.datasource.remote.models.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import id.asep.fpasep.datasource.repository.user.auth.UserAuthentication

@JsonClass(generateAdapter = true)
data class UserLoginRequest(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
) : UserAuthentication.AuthCredential
