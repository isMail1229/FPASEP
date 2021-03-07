package id.asep.fpasep.datasource.local.dao

import androidx.room.*
import id.asep.fpasep.datasource.local.models.Transaction

@Dao
interface TransactionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transactions: List<Transaction>): LongArray

    @Update
    suspend fun update(transaction: Transaction): Int

    @Query("SELECT COUNT(id) FROM `transaction`")
    suspend fun count(): Int
}