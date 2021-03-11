package id.asep.fpasep.datasource.remote.models.food

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListFood(
    @Json(name = "current_page") val page: Long,
    @Json(name = "data") val data: FoodResponse,

    )

/*Based on API (for while)*/
@JsonClass(generateAdapter = true)
data class FoodResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "picturePath") val foodImages: String?,
    @Json(name = "name") val foodName: String,
    @Json(name = "description") val foodDescription: String?,
    @Json(name = "ingredients") val foodIngredients: String?,
    @Json(name = "price") val foodPrice: Long,
    @Json(name = "rate") val foodRate: Long,
    @Json(name = "types") val foodType: String,
    @Json(name = "created_at") val createdAt: Long,
    @Json(name = "updated_at") val updatedAt: Long
)

