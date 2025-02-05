package com.example.kotlin_room_migration.data.repository

import com.example.kotlin_room_migration.data.dao.UserDao
import com.example.kotlin_room_migration.data.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val userDao: UserDao,
) {
    val users: Flow<List<User>> = userDao.getAll()

    suspend fun save(user: User) {
        userDao.insertAll(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.delete(user)
    }

    suspend fun updateUser(user: User) {
        userDao.update(user)
    }

    fun getUserById(id: Int): User? {
        return userDao.findById(id)
    }
}