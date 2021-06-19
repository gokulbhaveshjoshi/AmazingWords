package com.gokul.wordscore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gokul.wordscore.R
import com.gokul.wordscore.databinding.ItemScoreBinding
import com.gokul.wordscore.model.WordsItem

class WordAdapter(private val data: List<WordsItem>):
    RecyclerView.Adapter<WordAdapter.MyViewHolder>()
{
        class MyViewHolder(val binding: ItemScoreBinding):
                RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dm = data[position]
        ("Score is " + dm.score.toString()).also { holder.binding.tvScore.text = it }
        holder.binding.tvWord.text =  dm.word.toString()
    }

    override fun getItemCount(): Int = data.size

}