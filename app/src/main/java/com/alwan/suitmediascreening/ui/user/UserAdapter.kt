package com.alwan.suitmediascreening.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alwan.suitmediascreening.data.remote.model.User
import com.alwan.suitmediascreening.databinding.ItemUserBinding
import com.alwan.suitmediascreening.util.loadImage

class UserAdapter(private val listener: OnUserClickListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val mUsers = ArrayList<User>()

    fun addData(users: ArrayList<User>){
        mUsers.addAll(users)
        notifyDataSetChanged()
    }

    fun resetData(){
        mUsers.clear()
    }

    interface OnUserClickListener{
        fun onUserClick(data: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemUserBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemUserBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mUsers[position])
    }

    override fun getItemCount() = mUsers.size

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(user: User){
                with(binding){
                    with(user){
                        tvUserName.text = "$firstName $lastName"
                        tvUserEmail.text = email
                        imgUser.loadImage(avatar)
                        root.setOnClickListener{ listener.onUserClick(user) }
                    }
                }
            }
    }
}