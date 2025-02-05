package com.example.kotlin_room_migration.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin_room_migration.data.model.User
import com.example.kotlin_room_migration.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val users: LiveData<List<User>> = repository.users.asLiveData()

    fun getUserById(id: Int): LiveData<User?> {
        val response: MutableLiveData<User?> = MutableLiveData(null)
        viewModelScope.launch(Dispatchers.IO) {
            val retUser = repository.getUserById(id)
            response.postValue(retUser)
        }
        return response
    }

    fun deleteUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteUser(user)
    }

    // need id to update
    fun updateUser(userId: Int, userName: String, userEmail: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(userId, userName, userEmail)
            repository.updateUser(user)
        }
}