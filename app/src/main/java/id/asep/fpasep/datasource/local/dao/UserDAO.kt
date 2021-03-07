package id.asep.fpasep.datasource.local.dao

import androidx.room.*
import id.asep.fpasep.datasource.local.models.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<User>): LongArray

    @Update
    suspend fun update(user: User): Int

    @Query("SELECT COUNT(id) FROM user")
    suspend fun count(): Int

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Long): User?
}