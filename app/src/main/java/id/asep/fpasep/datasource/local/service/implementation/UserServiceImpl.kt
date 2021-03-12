package id.asep.fpasep.datasource.local.service.implementation

import id.asep.fpasep.datasource.local.dao.UserDAO
import id.asep.fpasep.datasource.local.models.User
import id.asep.fpasep.datasource.local.service.UserService
import javax.inject.Inject

class UserServiceImpl @Inject constructor(
    private val userDAO: UserDAO
) : UserService {

    override suspend fun insert(user: User): Long {
        return userDAO.insert(user)
    }

    override suspend fun insert(users: List<User>): LongArray {
        return userDAO.insert(users)
    }

    override suspend fun update(user: User): Int {
        return userDAO.update(user)
    }

    override suspend fun count(): Int {
        return userDAO.count()
    }

    override suspend fun getUserById(id: Long): User? {
        return userDAO.getUserById(id)
    }
}