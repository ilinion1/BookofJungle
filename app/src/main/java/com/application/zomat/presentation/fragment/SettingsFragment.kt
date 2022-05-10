package com.application.zomat.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.application.zomat.R
import com.application.zomat.databinding.FragmentGameBinding
import com.application.zomat.databinding.FragmentSettingsBinding
import com.application.zomat.presentation.MyGameViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity())[MyGameViewModel::class.java] }
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImage()
        setText()
        clickListenerLevel()
    }

    private fun setImage() {
        binding.imUser.visibility = View.GONE
        binding.imEasy.visibility = View.GONE
        binding.imNormal.visibility = View.GONE
        binding.imHard.visibility = View.GONE
        binding.imUser.setImageResource(R.drawable.user_icon)
        binding.imEasy.setImageResource(R.drawable.easy)
        binding.imNormal.setImageResource(R.drawable.normal)
        binding.imHard.setImageResource(R.drawable.hard)
        binding.imUser.visibility = View.VISIBLE
        binding.imEasy.visibility = View.VISIBLE
        binding.imNormal.visibility = View.VISIBLE
        binding.imHard.visibility = View.VISIBLE
    }

    private fun setText() {
        binding.tv1.visibility = View.GONE
        binding.tv2.visibility = View.GONE
        binding.tv3.visibility = View.GONE
        binding.tv4.visibility = View.GONE
        binding.tv1.setText(R.string.settings1)
        binding.tv2.setText(R.string.settings2)
        binding.tv3.setText(R.string.settings3)
        binding.tv4.setText(R.string.settings)
        binding.tv1.visibility = View.VISIBLE
        binding.tv2.visibility = View.VISIBLE
        binding.tv3.visibility = View.VISIBLE
        binding.tv4.visibility = View.VISIBLE
    }

    private fun clickListenerLevel() {
        binding.tv1.setOnClickListener {
            lifecycleScope.launch {
                it.alpha = 0.5F
                delay(200)
                it.alpha = 1F
            }
            viewModel.setGameLevel("Easy")
        }
        binding.imEasy.setOnClickListener {
            lifecycleScope.launch {
                it.alpha = 0.5F
                delay(200)
                it.alpha = 1F
            }
            viewModel.setGameLevel("Easy")
        }
        binding.tv2.setOnClickListener {
            lifecycleScope.launch {
                it.alpha = 0.5F
                delay(200)
                it.alpha = 1F
            }
            viewModel.setGameLevel("Normal")
        }
        binding.imNormal.setOnClickListener {
            lifecycleScope.launch {
                it.alpha = 0.5F
                delay(200)
                it.alpha = 1F
            }
            viewModel.setGameLevel("Normal")
        }
        binding.tv3.setOnClickListener {
            lifecycleScope.launch {
                it.alpha = 0.5F
                delay(200)
                it.alpha = 1F
            }
            viewModel.setGameLevel("Hard")
        }
        binding.imHard.setOnClickListener {
            lifecycleScope.launch {
                it.alpha = 0.5F
                delay(200)
                it.alpha = 1F
            }
            viewModel.setGameLevel("Hard")
        }
    }

    private fun setUserName(){
        if (!binding.edName.text.isNullOrEmpty()){
            viewModel.setUserName(binding.edName.text.toString())
        }
    }

    override fun onPause() {
        super.onPause()
        setUserName()
    }

}