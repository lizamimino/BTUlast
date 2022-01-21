package com.example.btuclassroom.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.btuclassroom.Tabs.FirstProfile
import com.example.btuclassroom.Tabs.SecondProfile

class viewPagerFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FirstProfile()
            1 -> SecondProfile()
            else -> FirstProfile()

        }

    }
}