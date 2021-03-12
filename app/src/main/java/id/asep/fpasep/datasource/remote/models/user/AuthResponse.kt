package id.asep.fpasep.datasource.remote.models.user

import com.squareup.moshi.Json

data class AuthResponse(
    @Json(name = "access_token") val accessToken: String?,
    @Json(name = "token_type") val tokenType: String,
    @Json(name = "user") val user: UserResponse
)