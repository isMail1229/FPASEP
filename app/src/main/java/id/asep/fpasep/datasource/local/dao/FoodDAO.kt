package id.asep.fpasep.datasource.local.dao

import androidx.room.*
import id.asep.fpasep.datasource.local.models.Food

@Dao
interface FoodDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(food: Food): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(foods: List<Food>): LongArray

    @Update
    suspend fun update(food: Food): Int

    @Query("SELECT COUNT(id) FROM food")
    suspend fun count(): Int
}