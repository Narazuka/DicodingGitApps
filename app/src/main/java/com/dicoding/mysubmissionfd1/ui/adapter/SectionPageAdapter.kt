@file:Suppress("DEPRECATION")

package com.dicoding.mysubmissionfd1.ui.adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.mysubmissionfd1.R
import com.dicoding.mysubmissionfd1.ui.detail_activity.FollowersFragment
import com.dicoding.mysubmissionfd1.ui.detail_activity.FollowingFragment

class SectionPageAdapter(private val mContext: Context, frm: FragmentManager, data: Bundle): FragmentPagerAdapter(frm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tabs_1, R.string.tabs_2)

    private var fragmentBundle: Bundle
    init {
        fragmentBundle = data
    }

    override fun getCount(): Int {
        return 2
    }
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> {
                fragment = FollowersFragment()
            }
            1 -> {
                fragment = FollowingFragment()
            }
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title = mContext.resources.getString(TAB_TITLES[position])
        return title
    }
}