package com.application.zomat.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.application.zomat.presentation.fragment.GameFragment
import com.application.zomat.presentation.fragment.InfoFragment
import com.application.zomat.presentation.fragment.ResultFragment
import com.application.zomat.presentation.fragment.SettingsFragment

class GameMenuAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> GameFragment()
            1 -> ResultFragment()
            2 -> SettingsFragment()
            else -> InfoFragment()
        }
    }
}