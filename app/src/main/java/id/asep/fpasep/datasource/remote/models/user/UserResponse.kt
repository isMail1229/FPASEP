package id.asep.fpasep.datasource.remote.models.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "email_verified_at") val emailVerifiedAt: String?,
    @Json(name = "roles") val roles: String,
    @Json(name = "address") val address: String?,
    @Json(name = "houseNumber") val houseNumber: String?,
    @Json(name = "phoneNumber") val phoneNumber: String?,
    @Json(name = "city") val city: String?,
    @Json(name = "created_at") val createdAt: Long,
    @Json(name = "updated_at") val updatedAt: Long,
    @Json(name = "profile_photo_url") val profilePhotoUrl: String?
)