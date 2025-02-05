package com.example.kotlin_room_migration.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_room_migration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var biding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)
    }
}