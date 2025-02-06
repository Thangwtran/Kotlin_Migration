package com.example.kotlin_room_migration.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.kotlin_room_migration.R
import com.example.kotlin_room_migration.ui.updateuser.UserUpdateActivity

// Alert Dialog
class UpdateDialogFragment(
    private val listener: UserUpdateActivity.UpdateUserListener,
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setMessage(getString(R.string.text_confirm_update))
        dialog.setPositiveButton(getString(R.string.text_btn_update)) { _, _ ->
            listener.update()
        }
        dialog.setNegativeButton(getString(R.string.text_btn_cancel)) { _, _ ->
            listener.cancel()
        }
        return dialog.create()
    }
}