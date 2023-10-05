package com.dicoding.mysubmissionfd1.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dicoding.mysubmissionfd1.data.response.User
import com.dicoding.mysubmissionfd1.databinding.ItemUsernameBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val listUsername = ArrayList<User>()
    private var onItemClickCallback: OnItemClickCallback? = null

   fun setOnItemCallback(onItemClickCallback: OnItemClickCallback){
       this.onItemClickCallback = onItemClickCallback
   }

   inner class UserViewHolder(private val binding: ItemUsernameBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(user.avatarurl)
                    .centerCrop()
                    .transform(CircleCrop())
                    .into(ciProfile)
                tvProfile.text = user.userLogin
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUsernameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listUsername.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val username = listUsername[position]
        holder.bind(username)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: List<User>){
        listUsername.clear()
        listUsername.addAll(users)
        notifyDataSetChanged()
    }
    interface OnItemClickCallback{
        fun onItemClicked(data: User)
    }
}