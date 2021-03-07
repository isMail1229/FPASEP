package id.asep.fpasep.datasource.local.dao

import androidx.room.*
import id.asep.fpasep.datasource.local.models.Rate

@Dao
interface RateDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rate: Rate): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rates: List<Rate>): LongArray

    @Update
    suspend fun update(rate: Rate): Int

    @Query("SELECT COUNT(id) FROM rate")
    suspend fun count(): Int
}