package com.example.kotlin_room_migration.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlin_room_migration.R
import com.example.kotlin_room_migration.databinding.ActivityUserDetailBinding
import com.example.kotlin_room_migration.ui.BaseActivity

class UserDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fillData()
        setAction()
    }

    private fun fillData() {
        val userId = intent.getIntExtra(USER_ID, 0)
        userViewmodel.getUserById(userId).observe(this){
            binding.editNameDetail.setText(it?.fullName)
            binding.editEmailDetail.setText(it?.email)
        }
    }

    private fun setAction() {
        binding.btnOk.setOnClickListener {
            finish()
        }
    }

    companion object{
        const val USER_ID = "user_id"
    }
}