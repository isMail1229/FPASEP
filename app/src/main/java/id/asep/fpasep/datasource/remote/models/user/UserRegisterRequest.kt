package id.asep.fpasep.datasource.remote.models.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRegisterRequest(
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "password_confirmation") val passwordConfirmation: String,
    @Json(name = "address") val address: String,
    @Json(name = "city") val city: String,
    @Json(name = "houseNumber") val houseNumber: String,
    @Json(name = "phoneNumber") val phoneNumber: String
)

