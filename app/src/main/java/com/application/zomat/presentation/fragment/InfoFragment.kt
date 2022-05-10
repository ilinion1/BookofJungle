package com.application.zomat.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.zomat.R
import com.application.zomat.databinding.FragmentGameBinding
import com.application.zomat.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNoMainImage()
        setMainImage()
        setTextVisibility()
        setText()
    }

    private fun setNoMainImage(){
        binding.im1.setImageResource(R.drawable.info1)
        binding.im2.setImageResource(R.drawable.info2)
        binding.im3.setImageResource(R.drawable.info3)
        binding.im4.setImageResource(R.drawable.info4)
        binding.im1.visibility = View.VISIBLE
        binding.im2.visibility = View.VISIBLE
        binding.im3.visibility = View.VISIBLE
        binding.im4.visibility = View.VISIBLE
        binding.im1.setImageResource(R.drawable.info1)
        binding.im2.setImageResource(R.drawable.info2)
        binding.im3.setImageResource(R.drawable.info3)
        binding.im4.setImageResource(R.drawable.info4)
        binding.im1.visibility = View.GONE
        binding.im2.visibility = View.GONE
        binding.im3.visibility = View.GONE
        binding.im4.visibility = View.GONE
    }

    private fun setMainImage(){
        binding.im5.setImageResource(R.drawable.info_01)
        binding.im6.setImageResource(R.drawable.info_02)
        binding.im7.setImageResource(R.drawable.info_03)
        binding.im8.setImageResource(R.drawable.info_04)
    }

    private fun setText(){
        binding.tv1.setText(R.string.info1)
        binding.tv2.setText(R.string.info2)
        binding.tv3.setText(R.string.info3)
        binding.tv4.setText(R.string.info4)
        binding.tvGoodGame.setText(R.string.info5)
    }

    private fun setTextVisibility(){
        binding.tv1.visibility = View.GONE
        binding.tv2.visibility = View.GONE
        binding.tv3.visibility = View.GONE
        binding.tv4.visibility = View.GONE
        binding.tvGoodGame.visibility = View.GONE
        binding.tv1.visibility = View.VISIBLE
        binding.tv2.visibility = View.VISIBLE
        binding.tv3.visibility = View.VISIBLE
        binding.tv4.visibility = View.VISIBLE
        binding.tvGoodGame.visibility = View.VISIBLE
    }
}