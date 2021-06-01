package com.lucascabral.marvelsuperheroes.presenter.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucascabral.marvelsuperheroes.data.network.model.video.VideoResponse
import com.lucascabral.marvelsuperheroes.databinding.ItemVideoBinding
import com.lucascabral.marvelsuperheroes.presenter.view.YoutubePlayerActivity

class YoutubeAdapter(private val videos: List<VideoResponse>) :
    RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        val viewBinding =
            ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return YoutubeViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        val video = videos[position]
        with(holder.binding) {
            titleTextView.text = video.snippet.title
            val uri = video.snippet.thumbnails.high.url
            Glide.with(videoImageView).load(uri).into(videoImageView)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, YoutubePlayerActivity::class.java)
            val videoId = video.id.videoId
            intent.putExtra(YoutubePlayerActivity.VIDEO_ID, videoId)
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount() = videos.count()

    inner class YoutubeViewHolder(val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root)
}