package com.dicoding.mysubmissionfd1.ui.detail_activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mysubmissionfd1.R
import com.dicoding.mysubmissionfd1.databinding.FollowersFragmentBinding
import com.dicoding.mysubmissionfd1.ui.adapter.UserAdapter
import com.dicoding.mysubmissionfd1.ui.detail_model.FollowersViewModel

class FollowersFragment: Fragment(R.layout.followers_fragment) {
    private var _binding: FollowersFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val  args = arguments
        username = args?.getString(DetailUsersActivity.EXTRA_USERNAME).toString()
        _binding = FollowersFragmentBinding.bind(view)



        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUsersfollow.setHasFixedSize(true)
            rvUsersfollow.layoutManager = LinearLayoutManager(activity)
            rvUsersfollow.adapter = adapter
        }
        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        viewModel.setUsernameFollowers(username)
        viewModel.getUsernameFollowers().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}