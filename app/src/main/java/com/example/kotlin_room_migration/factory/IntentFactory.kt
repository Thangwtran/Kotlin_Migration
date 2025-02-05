package com.example.kotlin_room_migration.factory

import android.content.Intent

interface IntentFactory<T> {
    fun createIntent(actionType: IntentActionType, obj: T?): Intent
}