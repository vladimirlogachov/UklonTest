package com.takeiteasy.vip.uklontest.models.comments

interface Comment {
    fun getCommentId(): Int
    fun getCommentAuthorName(): String
    fun getCommentAuthorEmail(): String
    fun getCommentBody(): String
}