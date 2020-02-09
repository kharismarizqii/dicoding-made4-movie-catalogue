package com.kharismarizqii.moviecatalogueuiux.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kharismarizqii.moviecatalogueuiux.fragment.FavoriteMovieFragment
import com.kharismarizqii.moviecatalogueuiux.fragment.FavoriteTVShowFragment
import com.kharismarizqii.moviecatalogueuiux.R

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.title_bar_1, R.string.title_bar_2)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FavoriteMovieFragment()
            1 -> fragment = FavoriteTVShowFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }

}