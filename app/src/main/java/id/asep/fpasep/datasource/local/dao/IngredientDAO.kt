package id.asep.fpasep.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import id.asep.fpasep.datasource.local.models.Ingredient

@Dao
interface IngredientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredient: Ingredient): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredients: List<Ingredient>): LongArray

    @Update
    suspend fun update(ingredient: Ingredient): Int

    suspend fun count(): Int
}