package com.lucascabral.marvelsuperheroes.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.databinding.ItemVideoBinding
import com.lucascabral.marvelsuperheroes.presenter.model.VideoUiModel

class YoutubeAdapter(
    private val videos: List<VideoUiModel>,
    private val onItemClickListenerVideo: ((video: VideoUiModel) -> Unit)
) : RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        val viewBinding =
            ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return YoutubeViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        val video = videos[position]
        val responseKindVideo = holder.itemView.context.getString(R.string.response_kind_video)
        if (video.id.kind == responseKindVideo) {
            with(holder) {
                binding.titleTextView.text = video.snippet.title
                val uri = video.snippet.thumbnails.high.url
                Glide.with(binding.videoImageView).load(uri).into(binding.videoImageView)

                itemView.setOnClickListener {
                    onItemClickListenerVideo.invoke(video)
                }
            }
        }
    }

    override fun getItemCount() = videos.count()

    inner class YoutubeViewHolder(
        val binding: ItemVideoBinding
    ) : RecyclerView.ViewHolder(binding.root)
}