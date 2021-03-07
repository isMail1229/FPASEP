package id.asep.fpasep.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import id.asep.fpasep.datasource.local.models.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<User>): LongArray

    @Update
    suspend fun update(user: User): Int

    suspend fun count(): Int

    suspend fun getUserById(id: Long): User?

    suspend fun getUserByServerId(serverId: Long): User?
}