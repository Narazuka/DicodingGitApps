package com.dicoding.mysubmissionfd1.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatDelegate

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mysubmissionfd1.R
import com.dicoding.mysubmissionfd1.data.response.User
import com.dicoding.mysubmissionfd1.databinding.ActivityMainBinding
import com.dicoding.mysubmissionfd1.ui.adapter.UserAdapter
import com.dicoding.mysubmissionfd1.ui.detail_activity.DetailUsersActivity
import com.dicoding.mysubmissionfd1.ui.detail_activity.FavoriteUserGitActivity
import com.dicoding.mysubmissionfd1.ui.detail_activity.SwitchActivity
import com.dicoding.mysubmissionfd1.ui.setting.SettingPreferences
import com.dicoding.mysubmissionfd1.ui.setting.SwitchModeViewMode
import com.dicoding.mysubmissionfd1.ui.setting.ViewModelFactory
import com.dicoding.mysubmissionfd1.ui.setting.dataStore


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTheme()
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        binding.topAppBar.setOnMenuItemClickListener{
            menuItem ->
            when(menuItem.itemId){
                R.id.menu1 -> {
                    val favUser = Intent(this, FavoriteUserGitActivity::class.java)
                    startActivity(favUser)
                    true
                }
                R.id.menu2 -> {
                    val switch = Intent(this, SwitchActivity::class.java)
                    startActivity(switch)
                    true

                }
                else -> false
            }
        }

        adapter.setOnItemCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUsersActivity::class.java).also {
                    it.putExtra(DetailUsersActivity.EXTRA_USERNAME, data.userLogin)
                    it.putExtra(DetailUsersActivity.EXTRA_ID, data.userId)
                    startActivity(it)
                }
            }

        })

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

        binding.apply {
            rvUsername.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUsername.setHasFixedSize(true)
            rvUsername.adapter = adapter

            btnSearch.setOnClickListener {
                searchUser()
            }
            etQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        viewModel.username.observe(this) { username ->
            if (username != null) {
                adapter.setList(username)
            }
            showLoading(false)
        }
    }
    private fun showLoading(isLoading: Boolean){
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
    private fun searchUser(){
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.getSearchUsername(query)
        }
    }
    private fun setTheme() {
        val pref = SettingPreferences.getInstance(application.dataStore)
        val switchModeViewModel = ViewModelProvider(
            this, ViewModelFactory(pref)
        )[SwitchModeViewMode::class.java]
        switchModeViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                false
            }
        }
    }
}