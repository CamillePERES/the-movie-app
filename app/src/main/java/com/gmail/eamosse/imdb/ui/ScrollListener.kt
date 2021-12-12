package com.gmail.eamosse.imdb.ui

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ScrollListener(recyclerView: RecyclerView, val loadMoreContent: () -> Unit): RecyclerView.OnScrollListener() {

    private var loading = false
    private val layoutManager: RecyclerView.LayoutManager = recyclerView.layoutManager!!
    private val extraScrollingThreshold = 3

    init {
        recyclerView.addOnScrollListener(this)
    }

    private fun canScroll(): Boolean {
        val lastVisibleItemPos = when (layoutManager) {
            is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
            is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
            else -> return false
        }
        return (lastVisibleItemPos + layoutManager.childCount + extraScrollingThreshold) < layoutManager.itemCount
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (!loading && !canScroll()) {
            loading = true
            loadMoreContent()
        }
    }

    fun itemsLoaded() {
        loading = false
    }
}
