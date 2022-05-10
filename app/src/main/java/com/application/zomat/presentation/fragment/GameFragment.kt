package com.application.zomat.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.zomat.R
import com.application.zomat.databinding.FragmentGameBinding
import com.application.zomat.presentation.MyGameViewModel
import com.application.zomat.presentation.adapter.GameWinAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GameFragment : Fragment() {
    lateinit var binding: FragmentGameBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity())[MyGameViewModel::class.java] }
    private val imageResult = listOf(
        R.drawable.game1, R.drawable.game2, R.drawable.game3,
        R.drawable.game4, R.drawable.game5, R.drawable.game6
    )
    private var numberImage = 0
    private var countLives = 5
    private var allBalls = 0
    lateinit var gameAdapter: GameWinAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameAdapter = GameWinAdapter()
        binding.rcGame.adapter = gameAdapter
        setView()
        setGameImage()
    }

    private fun setView() {
        binding.tv9.setText(R.string.game1)
        binding.tv6.setText(R.string.game2)
        binding.tvAllempts.setText(R.string.game3)
        binding.tvGameBals.setText(R.string.game4)
        viewModel.getUserName().observe(requireActivity()) {
            binding.tvUserName.text = it
        }
        binding.imageView.setImageResource(R.drawable.user_icon)
    }

    private fun setGameImage() {
        binding.btGame.setOnClickListener {
            if (countLives > 0) {
                binding.imGame.visibility = View.INVISIBLE
                binding.pgBar.visibility = View.VISIBLE
                lifecycleScope.launch {
                    delay(700)
                    binding.pgBar.visibility = View.INVISIBLE
                    binding.imGame.visibility = View.VISIBLE
                    numberImage = (0..5).random()
                    binding.imGame.setImageResource(imageResult[numberImage])
                    gameAdapter.gameList.add(imageResult[numberImage])
                    gameAdapter.notifyDataSetChanged()
                    setBalls()
                    binding.tvTrophies.visibility = View.VISIBLE
                }
            } else {
                viewModel.setGameResult(allBalls.toString())
                binding.tvNewGame.visibility = View.VISIBLE
                lifecycleScope.launch {
                    delay(700)
                    binding.tvNewGame.visibility = View.GONE
                    setView()
                    allBalls = 0
                    countLives = 5
                    binding.tvTrophies.visibility = View.GONE
                    gameAdapter.gameList.clear()
                    gameAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setBalls(){
        viewModel.getGameLevel().observe(requireActivity()){
            if (it == "Easy"){
                easyLevel()
            }
            if (it == "Normal"){
               normalLevel()
            }
            if (it == "Hard"){
               hardLevel()
            }
        }
    }

    private fun easyLevel(){
        when(numberImage){
            0 -> {
                binding.tvBals.text = "Good! Result: 9 balls!"
                allBalls += 9
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            1 -> {
                binding.tvBals.text = "Good! Result: 8 balls!"
                allBalls += 8
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            2 -> {
                binding.tvBals.text = "Good! Result: 7 balls!"
                allBalls += 7
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            3 -> {
                binding.tvBals.text = "Good! Result: 6 balls!"
                allBalls += 6
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            4 -> {
                binding.tvBals.text = "Good! Result: 5 balls!"
                allBalls += 5
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            else -> {
                binding.tvBals.text = "Good! Result: 10 balls!"
                allBalls += 10
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
        }
    }

    private fun normalLevel(){
        when(numberImage){
            0 -> {
                binding.tvBals.text = "Good! Result: 8 balls!"
                allBalls += 8
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            1 -> {
                binding.tvBals.text = "Good! Result: 7 balls!"
                allBalls += 7
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            2 -> {
                binding.tvBals.text = "Good! Result: 6 balls!"
                allBalls += 6
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            3 -> {
                binding.tvBals.text = "Good! Result: 5 balls!"
                allBalls += 5
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            4 -> {
                binding.tvBals.text = "Good! Result: 4 balls!"
                allBalls += 4
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            else -> {
                binding.tvBals.text = "Good! Result: 9 balls!"
                allBalls += 9
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
        }
    }

    private fun hardLevel(){
        when(numberImage){
            0 -> {
                binding.tvBals.text = "Good! Result: 7 balls!"
                allBalls += 7
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            1 -> {
                binding.tvBals.text = "Good! Result: 6 balls!"
                allBalls += 6
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            2 -> {
                binding.tvBals.text = "Good! Result: 5 balls!"
                allBalls += 5
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            3 -> {
                binding.tvBals.text = "Good! Result: 4 balls!"
                allBalls += 4
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            4 -> {
                binding.tvBals.text = "Good! Result: 3 balls!"
                allBalls += 3
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
            else -> {
                binding.tvBals.text = "Good! Result: 8 balls!"
                allBalls += 8
                binding.tvGameBals.text = allBalls.toString()
                countLives --
                binding.tvAllempts.text = countLives.toString()
            }
        }
    }
}