package com.example.kotlin_room_migration.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_room_migration.data.database.AppDatabase
import com.example.kotlin_room_migration.data.repository.UserRepository
import com.example.kotlin_room_migration.ui.viewmodel.UserViewModel

abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var userViewmodel: UserViewModel // protected -> class,package,subclass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewmodel()
    }

    private fun setupViewmodel() {
        val database by lazy { AppDatabase.getDatabase(this) }
        val repository by lazy { UserRepository(database.getUserDao()) }
        userViewmodel = UserViewModel(repository)
    }
}