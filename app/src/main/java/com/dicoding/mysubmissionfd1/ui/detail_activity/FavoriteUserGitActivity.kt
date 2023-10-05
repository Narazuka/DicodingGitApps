package com.dicoding.mysubmissionfd1.ui.detail_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mysubmissionfd1.data.response.User
import com.dicoding.mysubmissionfd1.databinding.ActivityFavoriteUserGitBinding
import com.dicoding.mysubmissionfd1.ui.adapter.UserAdapter
import com.dicoding.mysubmissionfd1.ui.detail_model.FavoriteViewModels

class FavoriteUserGitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteUserGitBinding
    private val favoriteViewModels by viewModels<FavoriteViewModels>()
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserGitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorite Kamuu"
        val layoutManager = LinearLayoutManager(this)
        binding.rvFav.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvFav.addItemDecoration(itemDecoration)
        isLoading(true)

        adapter = UserAdapter()
        adapter.setOnItemCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                Intent(this@FavoriteUserGitActivity, DetailUsersActivity::class.java).also {
                    it.putExtra(DetailUsersActivity.EXTRA_USERNAME, data.userLogin)
                    startActivity(it)
                }
            }

        })
        binding.rvFav.adapter = adapter

        favoriteViewModels.getFavUser()?.observe(this, {
            val map = it.map {
                User(
                    userLogin = it.username,
                    avatarurl =  it.avatarUrl
                )
            }
            adapter.setList(map)
        })
        isLoading(false)
    }
    private fun isLoading(isLoading: Boolean){
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}