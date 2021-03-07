package id.asep.fpasep.datasource.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient", inheritSuperIndices = true)
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "quantity") val quantity: Long,
    @ColumnInfo(name = "note") val note: String?,
    @ColumnInfo(name = "food_id") val foodId: Long,
)
