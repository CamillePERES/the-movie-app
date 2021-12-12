package com.gmail.eamosse.imdb.ui.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.api.response.DiscoverMovie
import com.gmail.eamosse.idbdata.api.response.ReviewsResponse
import com.gmail.eamosse.imdb.databinding.ReviewsItemBinding
import com.gmail.eamosse.imdb.ui.ScrollListener
import com.gmail.eamosse.imdb.ui.extension.*

class ReviewsAdapter(private val infiniteContentScrollListener: ScrollListener): ListAdapter<(ReviewsResponse),ReviewsAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ReviewsItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){

        val review: ReviewsResponse = getItem(position)
        holder.bind(review)

        val context = holder.itemView.context
        review.authorDetails?.avatarPath?.let { holder.binding.authorImage.bindPosterMovieCircle(it, context) }

        review.authorDetails?.username?.let{ holder.binding.authorName.text = review.authorDetails!!.username}

        review.content.let{ holder.binding.reviewContent.text = review.content}

        review.createdAt?.let{holder.binding.reviewDate.bindDateFormat(it)}

        review.authorDetails?.rating.let{holder.binding.ratingReviewBar.bindRatingBarReview(review.authorDetails,5)}

    }

    override fun submitList(list: List<ReviewsResponse>?) {
        val newList: MutableList<ReviewsResponse> = arrayListOf()
        if (list != null) newList.addAll(list)
        super.submitList(newList)
        infiniteContentScrollListener.itemsLoaded()
    }

    inner class ViewHolder(val binding: ReviewsItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ReviewsResponse) {
            binding.review = item
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<DiscoverMovie>() {
        override fun areItemsTheSame(oldItem: DiscoverMovie, newItem: DiscoverMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DiscoverMovie, newItem: DiscoverMovie): Boolean {
            return oldItem.id == newItem.id
        }
    }
}


