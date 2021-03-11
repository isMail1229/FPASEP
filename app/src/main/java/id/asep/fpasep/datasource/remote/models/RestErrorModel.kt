package id.asep.fpasep.datasource.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RestErrorModel(
    @Json(name = "message") val message: String?
)
