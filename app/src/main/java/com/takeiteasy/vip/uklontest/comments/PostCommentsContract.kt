package com.takeiteasy.vip.uklontest.comments

import com.takeiteasy.vip.uklontest.models.User
import com.takeiteasy.vip.uklontest.models.comments.Comment

interface PostCommentsContract {
    interface PostCommentsView {
        fun showProgress(show: Boolean)
        fun showError(error: String)
        fun showPostComments(posts: List<Comment>)
        fun showUserInfo(user: User)
    }

    interface PostCommentsPresenter {
        fun setPostCommentsView(view: PostCommentsView?)
        fun loadUserInfo(userId: Int)
        fun loadComments(postId: Int)
        fun refresh(postId: Int)
    }
}