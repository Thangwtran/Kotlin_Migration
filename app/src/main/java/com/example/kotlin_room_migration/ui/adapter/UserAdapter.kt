package com.example.kotlin_room_migration.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_room_migration.R
import com.example.kotlin_room_migration.data.model.User
import com.example.kotlin_room_migration.databinding.ItemUserBinding
import com.example.kotlin_room_migration.ui.MainActivity

class UserAdapter(
    private var users: List<User>?,
    private val listener: MainActivity.OptionMenuClickListener,
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    init {
        this.users = users ?: ArrayList()
    }
    class ViewHolder(
        v: View,
        private val biding: ItemUserBinding,
        private val listener: MainActivity.OptionMenuClickListener,
    ) : RecyclerView.ViewHolder(v) {
        private lateinit var user: User // for delete,update

        init {
            biding.btnOption.setOnClickListener { onOptionMenuClick() }
        }

        fun bindData(user: User) {
            this.user = user // assign user
            biding.textFullname.text = user.fullName
            biding.textEmail.text = user.email
            biding.textDisplayName.text = user.displayName
        }

        private fun onOptionMenuClick() {
            val popupMenu = PopupMenu(biding.root.context, biding.btnOption)
            popupMenu.inflate(R.menu.option_menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_item_delete -> {
                        listener.delete(user)
                        true
                    }

                    R.id.menu_item_update -> {
                        listener.update(user)
                        true
                    }
                    R.id.menu_item_detail->{
                        listener.viewDetail(user)
                        true
                    }
                    else -> false
                }
            }
            // show popup
            popupMenu.show()
        }
    }

    // update user
    fun updateData(data: List<User>) {
        this.users = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val biding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(biding.root, biding, listener)
    }

    override fun getItemCount(): Int {
        return users?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(users?.get(position)!!)
    }
}