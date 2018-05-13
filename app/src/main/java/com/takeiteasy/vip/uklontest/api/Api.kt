package com.takeiteasy.vip.uklontest.api

import com.takeiteasy.vip.uklontest.models.User
import com.takeiteasy.vip.uklontest.models.comments.PostComment
import com.takeiteasy.vip.uklontest.models.posts.RegularPost
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("posts")
    fun loadPosts(): Single<List<RegularPost>>

    @GET("posts/{id}/comments")
    fun loadPostComments(@Path("id") id: Int): Single<List<PostComment>>

    @GET("users/{id}")
    fun loadUserInfo(@Path("id") id: Int): Single<User>
}