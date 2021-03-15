package id.asep.fpasep.ui.authentication.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DialogDomain(
    val title: String,
    val description: String,
    val buttonText: String,
    val lottieState: Int
) : Parcelable