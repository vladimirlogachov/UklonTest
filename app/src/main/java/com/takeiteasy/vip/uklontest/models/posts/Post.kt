package com.takeiteasy.vip.uklontest.models.posts

interface Post {
    fun getAuthorUserId(): Int
    fun getPostId(): Int
    fun getPostTitle(): String
    fun getPostBody(): String
}