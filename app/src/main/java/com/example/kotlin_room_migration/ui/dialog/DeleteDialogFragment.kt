package com.example.kotlin_room_migration.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.kotlin_room_migration.R
import com.example.kotlin_room_migration.ui.MainActivity

class DeleteDialogFragment(
    private val listener: MainActivity.ActionConfirmListener,
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setMessage(getString(R.string.text_confirm_delete))
        dialog.setPositiveButton(getString(R.string.text_confirm)) { _, _ ->
            listener.confirm()
        }
        dialog.setNegativeButton(getString(R.string.text_cancel)) { _, _ ->
            listener.cancel()
        }
        return dialog.create()
    }
}