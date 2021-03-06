package id.asep.fpasep.datasource.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RestResponse<T>(
    @Json(name = "meta") val meta: MetaData,
    @Json(name = "data") val data: T?
)

@JsonClass(generateAdapter = true)
data class MetaData(
    @Json(name = "code") val code: Int,
    @Json(name = "status") val status: String,
    @Json(name = "message") val message: String
)