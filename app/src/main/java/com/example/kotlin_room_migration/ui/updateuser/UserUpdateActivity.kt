package com.example.kotlin_room_migration.ui.updateuser

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlin_room_migration.R
import com.example.kotlin_room_migration.databinding.ActivityUserUpdateBinding

class UserUpdateActivity : AppCompatActivity() {
    private lateinit var biding: ActivityUserUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityUserUpdateBinding.inflate(layoutInflater)
        setContentView(biding.root)
    }

    companion object{
        const val USER_ID = "user_id"
    }
}