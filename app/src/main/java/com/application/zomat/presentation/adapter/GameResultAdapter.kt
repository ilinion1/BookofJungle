package com.application.zomat.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.zomat.R

class GameResultAdapter: RecyclerView.Adapter<GameResultAdapter.GameResultViewHolder>() {

    private val imageResult = listOf(
        R.drawable.game1, R.drawable.game2, R.drawable.game3,
        R.drawable.game4, R.drawable.game5, R.drawable.game6)

    val gameResultList = arrayListOf<String>()

    class GameResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemResult: TextView = itemView.findViewById(R.id.tvResult)
        val imageResult: ImageView = itemView.findViewById(R.id.imResult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.result_item, parent, false)
        return GameResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameResultViewHolder, position: Int) {
        holder.itemResult.text = gameResultList[position]
        holder.imageResult.setImageResource(imageResult[(0..5).random()])
    }

    override fun getItemCount(): Int {
        return gameResultList.size
    }
}