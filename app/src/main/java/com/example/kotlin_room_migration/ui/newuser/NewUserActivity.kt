package com.example.kotlin_room_migration.ui.newuser

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlin_room_migration.R
import com.example.kotlin_room_migration.databinding.ActivityNewUserBinding

class NewUserActivity : AppCompatActivity() {
    private lateinit var biding: ActivityNewUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(biding.root)
    }
}