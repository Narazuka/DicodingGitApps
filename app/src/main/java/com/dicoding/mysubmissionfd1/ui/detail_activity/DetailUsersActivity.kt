package com.dicoding.mysubmissionfd1.ui.detail_activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View

import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.mysubmissionfd1.R
import com.dicoding.mysubmissionfd1.databinding.ActivityDetailUsersBinding
import com.dicoding.mysubmissionfd1.ui.adapter.SectionPageAdapter
import com.dicoding.mysubmissionfd1.ui.detail_model.DetailUsersViewModel

class DetailUsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUsersBinding
    private lateinit var viewModel: DetailUsersViewModel
    private val handler = Handler(Looper.getMainLooper())
    private var favUserStatus: Boolean = false



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val users = intent.getStringExtra(EXTRA_USERNAME)

        var image: String = "www.google.com"
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, users)


        viewModel = ViewModelProvider(this,
        ).get(DetailUsersViewModel::class.java)


        if (users != null) {
            viewModel.setDetailUsers(users)
            viewModel = ViewModelProvider(this).get(DetailUsersViewModel::class.java)
            showLoading(true)
            handler.post {
                viewModel.getUserDetails().observe(this) { detailUsername ->
                    if (detailUsername != null) {
                        binding.apply {
                            tvProfile.text = detailUsername.userLogin
                            tvUser.text = detailUsername.username
                            userfollowers.text = "${detailUsername.userFollowers} Followers"
                            userfollowing.text = "${detailUsername.userFollowing} Following"
                            Glide.with(this@DetailUsersActivity)
                                .load(detailUsername.avatarUrl)
                                .centerCrop()
                                .into(ciProfile)
                            image = detailUsername.avatarUrl
                        }
                        showLoading(false)
                    } else {
                        println("Nothing Username")
                    }
                    viewModel.getFavData(detailUsername.userLogin)?.observe(this){
                        if (it == null){
                            binding.fabAdd.setImageResource(R.drawable.ic_favadd)
                            favUserStatus = false
                        } else {
                            binding.fabAdd.setImageResource(R.drawable.ic_favorite)
                            favUserStatus = true
                        }
                    }
                }
            }

        }
        binding.fabAdd.setOnClickListener {
            favUserStatus = if (favUserStatus == true){
                users?.let { it1 -> viewModel.removeFav(it1) }
                binding.fabAdd.setImageResource(com.dicoding.mysubmissionfd1.R.drawable.ic_favadd)
                false
            } else {
                users?.let { it1 -> viewModel.addToFav(it1, image) }
                binding.fabAdd.setImageResource(com.dicoding.mysubmissionfd1.R.drawable.ic_favorite)
                true
            }
        }

        val sectionPageAdapter = SectionPageAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPage.adapter = sectionPageAdapter
            tabsPage.setupWithViewPager(viewPage)
        }
    }
    private fun showFavFloatButton(isFavorite: Boolean){
        binding.fabAdd.setImageResource(if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favadd)
    }
    private fun showLoading(isLoading: Boolean){
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
    companion object{
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
    }
}