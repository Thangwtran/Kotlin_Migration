package com.example.kotlin_room_migration.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.kotlin_room_migration.R
import com.example.kotlin_room_migration.data.model.User
import com.example.kotlin_room_migration.databinding.ActivityMainBinding
import com.example.kotlin_room_migration.factory.IntentActionType
import com.example.kotlin_room_migration.factory.IntentFactoryImp
import com.example.kotlin_room_migration.ui.adapter.UserAdapter
import com.example.kotlin_room_migration.ui.dialog.DeleteDialogFragment
import com.google.android.material.snackbar.Snackbar

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myLauncher: ActivityResultLauncher<Intent>
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupListener()
    }

    private fun setupListener() {
        myLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            this::handleResult
        )
        binding.btnAddNew.setOnClickListener {
            val intent = IntentFactoryImp<User>(baseContext)
                .createIntent(IntentActionType.CREATE,null)
            myLauncher.launch(intent)
        }
    }

    private fun handleResult(activityResult: ActivityResult) {
        if (activityResult.resultCode == RESULT_OK) {
            Snackbar.make(binding.root, getString(R.string.text_add_success), Snackbar.LENGTH_SHORT)
                .show()
        } else {
            Snackbar.make(binding.root, getString(R.string.text_add_failed), Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView() {
        binding.recyclerUser.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(userViewmodel.users.value,
            object : OptionMenuClickListener {
                override fun update(user: User) {
                    val intent = IntentFactoryImp<User>(baseContext)
                        .createIntent(IntentActionType.EDIT, user)
                    startActivity(intent)
                }
                override fun delete(user: User) {
                    // show delete dialog
                    DeleteDialogFragment(object : ActionConfirmListener {
                        override fun confirm() {
                            userViewmodel.deleteUser(user)
                        }
                        override fun cancel() {
                            // do nothing
                        }
                    }).show(supportFragmentManager, "delete_dialog")
                }

                override fun viewDetail(user: User) {
                    val intent = IntentFactoryImp<User>(baseContext)
                        .createIntent(IntentActionType.VIEW_DETAIL, user)
                    startActivity(intent)
                }
            })
        binding.recyclerUser.adapter = adapter
        userViewmodel.users.observe(this) {
            adapter.updateData(it)
            adapter.notifyDataSetChanged()
        }
        val itemDeclaration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.recyclerUser.addItemDecoration(itemDeclaration)
    }

    interface ActionConfirmListener {
        fun confirm()
        fun cancel()
    }

    interface OptionMenuClickListener {
        fun update(user: User)
        fun delete(user: User)
        fun viewDetail(user: User)
    }
}