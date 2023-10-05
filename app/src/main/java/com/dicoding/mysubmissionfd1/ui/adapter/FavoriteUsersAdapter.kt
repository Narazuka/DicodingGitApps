package com.dicoding.mysubmissionfd1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.mysubmissionfd1.data.response.User
import com.dicoding.mysubmissionfd1.databinding.ItemUsernameBinding

class FavoriteUsersAdapter(private val itemClickListener: OnItemClickListener)
    :ListAdapter<User, FavoriteUsersAdapter.MyViewHolder>(DIFFCALLBACK) {
    inner class MyViewHolder(private val binding: ItemUsernameBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val user = getItem(position)
                    itemClickListener.onItemClick(user)
                }
            }
        }
        fun bind(favUsername: User){
            binding.tvProfile.text = "${favUsername.userLogin}"
            Glide.with(binding.root)
                .load("${favUsername.avatarurl}")
                .into(binding.ciProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUsernameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    interface OnItemClickListener{
        fun onItemClick(user: User)
    }
    companion object{
        val DIFFCALLBACK = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}