package com.takeiteasy.vip.uklontest.main

import com.takeiteasy.vip.uklontest.models.posts.Post

interface MainContract {
    interface MainView {
        fun showProgress(show: Boolean)
        fun showError(error: String)
        fun showPosts(posts: List<Post>)
    }

    interface MainPresenter {
        fun setMainView(view: MainView?)
        fun loadPosts()
        fun refresh()
    }
}