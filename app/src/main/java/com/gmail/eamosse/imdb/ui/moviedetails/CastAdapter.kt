package com.gmail.eamosse.imdb.ui.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.api.response.CastResponse
import com.gmail.eamosse.imdb.databinding.CastItemBinding
import com.gmail.eamosse.imdb.ui.extension.bindCastPoster

class CastAdapter (private val items:List<CastResponse>): RecyclerView.Adapter<CastAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(CastItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: CastAdapter.ViewHolder, position: Int) {

        val cast: CastResponse = items[position]
        holder.bind(cast)

        holder.binding.titleText.text = cast.name

        val context = holder.binding.image.context
        cast.profilePath?.let { holder.binding.image.bindCastPoster(it, context) }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: CastItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(item: CastResponse) {
            binding.cast = item
        }
    }


}