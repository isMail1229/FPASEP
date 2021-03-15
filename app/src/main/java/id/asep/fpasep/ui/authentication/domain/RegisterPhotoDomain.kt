package id.asep.fpasep.ui.authentication.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterPhotoDomain(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val pathProfileImg: String?
) : Parcelable