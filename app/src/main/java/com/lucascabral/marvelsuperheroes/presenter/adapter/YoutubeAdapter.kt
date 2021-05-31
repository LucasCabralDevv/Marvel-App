package com.lucascabral.marvelsuperheroes.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucascabral.marvelsuperheroes.databinding.ItemVideoBinding
import com.lucascabral.marvelsuperheroes.presenter.model.VideoUiModel

class YoutubeAdapter(private val videos: List<VideoUiModel>) :
    RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        val viewBinding =
            ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return YoutubeViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        val video = videos[position]
        with(holder.binding) {
            titleTextView.text = video.title
        }
    }

    override fun getItemCount() = videos.count()

    inner class YoutubeViewHolder(val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root)
}