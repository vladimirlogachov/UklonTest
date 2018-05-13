package com.takeiteasy.vip.uklontest.storage

import android.util.SparseArray
import com.takeiteasy.vip.uklontest.models.comments.PostComment
import com.takeiteasy.vip.uklontest.models.posts.RegularPost

class Storage {
    private var regularPosts: MutableList<RegularPost> = mutableListOf()
    private var postComments: SparseArray<List<PostComment>> = SparseArray()

    fun saveRegularPosts(posts: List<RegularPost>) {
        regularPosts.addAll(posts)
    }

    fun loadRegularPosts(): List<RegularPost> {
        return regularPosts
    }

    fun removeRegularPosts() {
        regularPosts.clear()
    }

    fun containsRegularPosts(): Boolean {
        return regularPosts.isNotEmpty()
    }

    fun removePostComments(postId: Int) {
        postComments.remove(postId)
    }

    fun loadPostComments(postId: Int): List<PostComment> {
        return postComments.get(postId)
    }

    fun savePostComments(postId: Int, comments: List<PostComment>) {
        postComments.put(postId, comments)
    }

    fun containsPostComments(postId: Int): Boolean {
        return postComments.indexOfKey(postId) >= 0
    }
}