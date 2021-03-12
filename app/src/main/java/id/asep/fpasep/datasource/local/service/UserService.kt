package id.asep.fpasep.datasource.local.service

import id.asep.fpasep.datasource.local.models.User

interface UserService {

    suspend fun insert(user: User): Long

    suspend fun insert(users: List<User>): LongArray

    suspend fun update(user: User): Int

    suspend fun count(): Int

    suspend fun getUserById(id: Long): User?
}