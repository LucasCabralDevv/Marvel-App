package com.lucascabral.marvelsuperheroes.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucascabral.marvelsuperheroes.data.network.model.comics.Comic
import com.lucascabral.marvelsuperheroes.databinding.ItemComicBinding

class ComicsAdapter(
    private val comics: List<Comic>
): RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val viewBinding =
            ItemComicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicsViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val comic = comics[position]
        with(holder.binding) {
            val uri = comic.thumbnail.path+"."+comic.thumbnail.extension
            Glide.with(comicThumbImageView).load(uri).into(comicThumbImageView)
        }
    }

    override fun getItemCount(): Int = comics.size

    inner class ComicsViewHolder(
        val binding: ItemComicBinding
    ): RecyclerView.ViewHolder(binding.root)
}