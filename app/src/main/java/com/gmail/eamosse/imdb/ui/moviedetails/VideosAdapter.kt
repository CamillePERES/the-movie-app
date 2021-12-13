package com.gmail.eamosse.imdb.ui.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.api.response.VideosResponse
import com.gmail.eamosse.imdb.databinding.VideoItemBinding
import com.gmail.eamosse.imdb.ui.extension.bindVideoPoster

class VideosAdapter(private val items:List<VideosResponse>, private val listener:IVideosListener): RecyclerView.Adapter<VideosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(VideoItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: VideosAdapter.ViewHolder, position: Int) {
        val video: VideosResponse = items[position]
        holder.bind(video)

        val context = holder.binding.image.context
        video.key?.let { holder.binding.image.bindVideoPoster(it, context) }

        holder.binding.root.setOnClickListener {
            listener.onClickVideo(video)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: VideoItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(item: VideosResponse) {
            binding.video = item
        }
    }
}