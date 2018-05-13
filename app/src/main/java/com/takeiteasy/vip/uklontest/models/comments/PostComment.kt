package com.takeiteasy.vip.uklontest.models.comments

data class PostComment(
        val postId: Int,
        val id: Int,
        val name: String,
        val email: String,
        val body: String
) : Comment {
    override fun getCommentId(): Int {
        return id
    }

    override fun getCommentAuthorName(): String {
        return name
    }

    override fun getCommentAuthorEmail(): String {
        return email
    }

    override fun getCommentBody(): String {
        return body
    }
}