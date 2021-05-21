package com.lucascabral.marvelsuperheroes.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.network.model.Character

class AllCharactersAdapter: PagingDataAdapter<Character, AllCharactersAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return MyViewHolder(view)
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val imageCharacter: ImageView = view.findViewById(R.id.characterImage)
        private val nameCharacter: TextView = view.findViewById(R.id.characterName)

        fun bind(data: Character) {
            nameCharacter.text = data.name
            val uri = data.thumbnail.path+"."+data.thumbnail.extension
            Glide.with(imageCharacter).load(uri).into(imageCharacter)
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == oldItem.name
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            val oldImage = oldItem.thumbnail.path+"."+oldItem.thumbnail.extension
            val newImage = newItem.thumbnail.path+"."+newItem.thumbnail.extension
            return oldItem.name == newItem.name && oldImage == newImage
        }
    }
}