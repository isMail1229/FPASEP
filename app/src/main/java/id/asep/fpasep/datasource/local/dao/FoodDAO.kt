package id.asep.fpasep.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import id.asep.fpasep.datasource.local.models.Food

@Dao
interface FoodDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(food: Food): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(foods: List<Food>): LongArray

    @Update
    suspend fun update(food: Food): Int

    suspend fun count(): Int
}