package id.asep.fpasep.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import id.asep.fpasep.datasource.local.models.Transaction

@Dao
interface TransactionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transactions: List<Transaction>): LongArray

    @Update
    suspend fun update(transaction: Transaction): Int

    suspend fun count(): Int
}