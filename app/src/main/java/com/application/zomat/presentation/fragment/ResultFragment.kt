package com.application.zomat.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.application.zomat.R
import com.application.zomat.databinding.FragmentResultBinding
import com.application.zomat.presentation.MyGameViewModel
import com.application.zomat.presentation.adapter.GameResultAdapter


class ResultFragment : Fragment() {
    lateinit var binding: FragmentResultBinding
    lateinit var resultAdapter: GameResultAdapter
    private val viewModel by lazy { ViewModelProvider(requireActivity())[MyGameViewModel::class.java] }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setVisibilityView()
        setAdapter()
    }

    override fun onPause() {
        super.onPause()
        setAdapter()
    }

    private fun setAdapter(){
        resultAdapter = GameResultAdapter()
        viewModel.getGameResult().observe(requireActivity()){
            resultAdapter.gameResultList.add(it)
            resultAdapter.notifyItemRangeChanged(0, resultAdapter.gameResultList.size -1)
        }
        binding.rcId.adapter = resultAdapter
        binding.rcId.layoutManager = GridLayoutManager(context, 2)
    }

    private fun setVisibilityView(){
        binding.textView4.setText(R.string.result)
        binding.tv1.visibility = View.GONE
        binding.tv2.visibility = View.GONE
        binding.tv3.visibility = View.GONE
        binding.tv4.visibility = View.GONE
        binding.imEasy.visibility = View.GONE
        binding.imHard.visibility = View.GONE
        binding.imNormal.visibility = View.GONE
        binding.tv1.visibility = View.VISIBLE
        binding.tv2.visibility = View.VISIBLE
        binding.tv3.visibility = View.VISIBLE
        binding.tv4.visibility = View.VISIBLE
        binding.imEasy.visibility = View.VISIBLE
        binding.imHard.visibility = View.VISIBLE
        binding.imNormal.visibility = View.VISIBLE
        binding.tv1.visibility = View.GONE
        binding.tv2.visibility = View.GONE
        binding.tv3.visibility = View.GONE
        binding.tv4.visibility = View.GONE
        binding.imEasy.visibility = View.GONE
        binding.imHard.visibility = View.GONE
        binding.imNormal.visibility = View.GONE
    }
}