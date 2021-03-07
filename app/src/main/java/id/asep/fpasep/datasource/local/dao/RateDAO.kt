package id.asep.fpasep.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import id.asep.fpasep.datasource.local.models.Rate

@Dao
interface RateDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rate: Rate): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rates: List<Rate>): LongArray

    @Update
    suspend fun update(rate: Rate): Int

    suspend fun count(): Int
}