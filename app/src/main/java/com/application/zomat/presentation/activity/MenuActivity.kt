package com.application.zomat.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.application.zomat.R
import com.application.zomat.databinding.ActivityMenuBinding
import com.application.zomat.presentation.adapter.GameMenuAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    lateinit var viewPagerAdapter: GameMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewPagerAdapter()
        setTabLayout()
    }

    private fun setViewPagerAdapter() {
        viewPagerAdapter = GameMenuAdapter(this)
        binding.viewPagId.adapter = viewPagerAdapter
    }

    private fun setTabLayout(){
        binding.tabLayoutId.tabIconTint = null
        TabLayoutMediator(binding.tabLayoutId, binding.viewPagId){ tab, pos ->
            when(pos){
                0->{
                    tab.setIcon(R.drawable.ic_game)
                    tab.icon?.setTint(ContextCompat.getColor(this, R.color.tab_menu_icon))
                }
                1->{
                    tab.setIcon(R.drawable.ic_result)
                    tab.icon?.setTint(ContextCompat.getColor(this@MenuActivity, R.color.grey))
                }
                2->{
                    tab.setIcon(R.drawable.ic_settings)
                    tab.icon?.setTint(ContextCompat.getColor(this@MenuActivity, R.color.grey))
                }
                else->{
                    tab.setIcon(R.drawable.ic_info)
                    tab.icon?.setTint(ContextCompat.getColor(this@MenuActivity, R.color.grey))
                }
            }
        }.attach()

        binding.tabLayoutId.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(ContextCompat.getColor(this@MenuActivity, R.color.tab_menu_icon))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(ContextCompat.getColor(this@MenuActivity, R.color.grey))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onStart() {
        super.onStart()
        setVisibilityView()
        setText()
    }


    private fun setVisibilityView() {
        binding.tv.visibility = View.GONE
        binding.tv1.visibility = View.GONE
        binding.tv2.visibility = View.GONE
        binding.tv3.visibility = View.GONE
        binding.tv4.visibility = View.GONE
        binding.tv.visibility = View.VISIBLE
        binding.tv1.visibility = View.VISIBLE
        binding.tv2.visibility = View.VISIBLE
        binding.tv3.visibility = View.VISIBLE
        binding.tv4.visibility = View.VISIBLE
        binding.tv.visibility = View.GONE
        binding.tv1.visibility = View.GONE
        binding.tv2.visibility = View.GONE
        binding.tv3.visibility = View.GONE
        binding.tv4.visibility = View.GONE
    }

    private fun setText(){
        binding.tv.text = ""
        binding.tv1.text = ""
        binding.tv2.text = ""
        binding.tv3.text = ""
        binding.tv4.text = ""
        binding.tv.text = "game1"
        binding.tv1.text = "game1"
        binding.tv2.text = "game3"
        binding.tv3.text = "game4"
        binding.tv4.text = "game5"
        binding.tv.text = ""
        binding.tv1.text = ""
        binding.tv2.text = ""
        binding.tv3.text = ""
        binding.tv4.text = ""
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}