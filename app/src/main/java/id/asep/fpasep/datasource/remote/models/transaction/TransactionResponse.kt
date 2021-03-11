package id.asep.fpasep.datasource.remote.models.transaction

import com.squareup.moshi.Json

data class TransactionResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "food_id") val foodId: Long,
    @Json(name = "user_id") val userId: Long,
    @Json(name = "quantity") val quantity: Long,
    @Json(name = "total") val total: Long,
    @Json(name = "status") val status: String,
    @Json(name = "payment_url") val paymentUrl: String,
    @Json(name = "created_at") val createdAt: Long,
    @Json(name = "updated_at") val updatedAt: Long
)
