package com.example.kotlin_room_migration.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_room_migration.data.model.User
import com.example.kotlin_room_migration.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewUserViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    fun saveUser(fullName: String, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.save(User(0, fullName, email))
        }
    }
}