package com.example.kotlin_room_migration.ui.updateuser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_room_migration.R
import com.example.kotlin_room_migration.databinding.ActivityUserUpdateBinding
import com.example.kotlin_room_migration.ui.BaseActivity
import com.example.kotlin_room_migration.ui.dialog.UpdateDialogFragment

class UserUpdateActivity : BaseActivity() {
    private lateinit var binding: ActivityUserUpdateBinding
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.containerLayout.btnAdd.text = getString(R.string.text_btn_update)
        fillData(intent)
        addListeners()
    }

    private fun addListeners() {
        binding.containerLayout.btnAdd.setOnClickListener {
            val fullName = binding.containerLayout.editFullname.text.toString()
            val email = binding.containerLayout.editEmail.text.toString()
            UpdateDialogFragment(object : UpdateUserListener {
                override fun update() {
                    userViewmodel.updateUser(userId, fullName, email)
                    finish()
                }

                override fun cancel() {
                    finish()
                }
            }).show(supportFragmentManager,"update_dialog")
        }
        binding.containerLayout.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun fillData(intent: Intent) {
        userId = intent.getIntExtra(USER_ID, 0)
        userViewmodel.getUserById(userId).observe(this) {
            binding.containerLayout.editFullname.setText(it?.fullName)
            binding.containerLayout.editEmail.setText(it?.email)
        }
    }

    interface UpdateUserListener {
        fun update()
        fun cancel()
    }

    companion object {
        const val USER_ID = "user_id"
    }
}