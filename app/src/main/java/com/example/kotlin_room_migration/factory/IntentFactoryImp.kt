package com.example.kotlin_room_migration.factory

import android.content.Context
import android.content.Intent

class IntentFactoryImp<T>(private val context: Context) : IntentFactory<T> {
    override fun createIntent(actionType: IntentActionType, obj: T?): Intent {

    }
}