package com.takeiteasy.vip.uklontest.main

import android.support.annotation.LayoutRes
import android.support.annotation.Nullable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.takeiteasy.vip.uklontest.R
import com.takeiteasy.vip.uklontest.models.posts.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter(private val listener: (Int, Int) -> Unit) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    private val data: MutableList<Post> = mutableListOf()

    fun updateData(data: List<Post>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflateView(parent, R.layout.item_post))
    }

    private fun inflateView(parent: ViewGroup, @LayoutRes layoutRes: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: Post, listener: (Int, Int) -> Unit) = with(itemView) {
            postTitle.text = post.getPostTitle()
            postBody.text = post.getPostBody()
            setOnClickListener{listener(post.getPostId(), post.getAuthorUserId())}
        }
    }
}