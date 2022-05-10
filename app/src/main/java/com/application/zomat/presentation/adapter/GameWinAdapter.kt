package com.application.zomat.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.application.zomat.R

class GameWinAdapter: RecyclerView.Adapter<GameWinAdapter.GameHolder>() {

    val gameList = arrayListOf<Int>()

    class GameHolder(view: View): RecyclerView.ViewHolder(view) {
        val imMain: ImageView = view.findViewById(R.id.imGame1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        return GameHolder(view)
    }

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        holder.imMain.setImageResource(gameList[position])
    }

    override fun getItemCount(): Int {
        return gameList.size
    }
}