package com.takeiteasy.vip.uklontest.models.posts

data class RegularPost(
        val userId: Int,
        val id: Int,
        val title: String,
        val body: String
) : Post {
    override fun getAuthorUserId(): Int {
        return userId
    }

    override fun getPostId(): Int {
        return id
    }

    override fun getPostTitle(): String {
        return title
    }

    override fun getPostBody(): String {
        return body
    }
}