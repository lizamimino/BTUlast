package com.example.btuclassroom.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.btuclassroom.R
import com.example.btuclassroom.adapters.viewPagerFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfilFragment : Fragment(R.layout.fragment_profile){

    private lateinit var viewPager2 : ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerFragmentAdapter: viewPagerFragmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        viewPager2.adapter = viewPagerFragmentAdapter
        TabLayoutMediator(tabLayout, viewPager2){tab, position ->
            tab.text = "tab ${position+1}"
        }.attach()
    }

    private fun init(){
        viewPager2 = requireView().findViewById(R.id.viewPager2)
        tabLayout = requireView().findViewById(R.id.tabLaout)
        viewPagerFragmentAdapter = viewPagerFragmentAdapter(requireActivity())
    }
}