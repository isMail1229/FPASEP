package id.asep.fpasep.datasource.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "food", inheritSuperIndices = true)
data class Food(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: Long,
    @ColumnInfo(name = "price") val price: Long,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "food_photo") val foodPhoto: String?,
) {

    class FoodConverter {
        @TypeConverter
        fun fromStringToType(value: String): FoodTypes {
            return when (value) {
                FoodTypes.New.codeNames -> FoodTypes.New
                FoodTypes.Recommended.codeNames -> FoodTypes.Recommended
                FoodTypes.Popular.codeNames -> FoodTypes.Popular
                else -> FoodTypes.New
            }
        }
    }

    open class FoodTypes(val codeNames: String) {
        object New : FoodTypes("new_food")
        object Recommended : FoodTypes("recommended")
        object Popular : FoodTypes("popular")
    }
}