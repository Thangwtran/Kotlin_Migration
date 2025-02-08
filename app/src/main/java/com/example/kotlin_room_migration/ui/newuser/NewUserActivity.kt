package com.example.kotlin_room_migration.ui.newuser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_room_migration.data.database.AppDatabase
import com.example.kotlin_room_migration.data.repository.UserRepository
import com.example.kotlin_room_migration.databinding.ActivityNewUserBinding
import com.example.kotlin_room_migration.ui.viewmodel.NewUserViewModel

class NewUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewUserBinding
    private lateinit var viewmodel : NewUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupListener()
    }
    private fun setupViewModel() {
        val database by lazy { AppDatabase.getDatabase(this) }
        val repository by lazy { UserRepository(database.getUserDao()) }
        viewmodel = NewUserViewModel(repository) // should initialize by ViewModelProvider
    }
    private fun setupListener() {
        binding.btnAdd.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val fullName = binding.editFullname.text.toString()
            val displayName = binding.editDisplayName.text.toString()
            viewmodel.saveUser(fullName,email,displayName)
            setResult(RESULT_OK, Intent())
            finish()
        }
        binding.btnCancel.setOnClickListener { finish() }
    }
}