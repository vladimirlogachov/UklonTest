package com.takeiteasy.vip.uklontest.comments

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.takeiteasy.vip.uklontest.R
import com.takeiteasy.vip.uklontest.models.comments.Comment
import kotlinx.android.synthetic.main.item_post_comment.view.*

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    private val data: MutableList<Comment> = mutableListOf()

    fun updateData(data: List<Comment>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflateView(parent, R.layout.item_post_comment))
    }

    private fun inflateView(parent: ViewGroup, @LayoutRes layoutRes: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comment: Comment) = with(itemView) {
            postCommentAuthorName.text = comment.getCommentAuthorName()
            postCommentAuthorEmail.text = comment.getCommentAuthorEmail()
            postCommentBody.text = comment.getCommentBody()
        }
    }
}