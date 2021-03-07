package id.asep.fpasep.datasource.local.dao

import androidx.room.*
import id.asep.fpasep.datasource.local.models.Ingredient

@Dao
interface IngredientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredient: Ingredient): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredients: List<Ingredient>): LongArray

    @Update
    suspend fun update(ingredient: Ingredient): Int

    @Query("SELECT COUNT(id) FROM ingredient")
    suspend fun count(): Int
}