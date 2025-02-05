package com.example.kotlin_room_migration.factory

import android.content.Context
import android.content.Intent
import com.example.kotlin_room_migration.data.model.User
import com.example.kotlin_room_migration.ui.detail.UserDetailActivity
import com.example.kotlin_room_migration.ui.newuser.NewUserActivity
import com.example.kotlin_room_migration.ui.updateuser.UserUpdateActivity

class IntentFactoryImp<T>(private val context: Context) : IntentFactory<T> {
    override fun createIntent(actionType: IntentActionType, obj: T?): Intent {
        return when (actionType) {
            IntentActionType.CREATE -> createIntentToAddNewUser()
            IntentActionType.EDIT -> createIntentToEdit(obj)
            IntentActionType.VIEW_DETAIL -> createIntentToViewDetail(obj)
        }
    }

    private fun createIntentToAddNewUser(): Intent {
        return Intent(context, NewUserActivity::class.java)
    }

    private fun createIntentToEdit(obj: T?): Intent {
        return Intent(context, UserUpdateActivity::class.java).apply {
            val user = obj as? User
            putExtra(UserUpdateActivity.USER_ID, user?.id)
        }
    }

    private fun createIntentToViewDetail(obj: T?): Intent {
        return Intent(context, UserDetailActivity::class.java).apply {
            val user = obj as? User
            putExtra(UserUpdateActivity.USER_ID, user?.id)
        }
    }


}