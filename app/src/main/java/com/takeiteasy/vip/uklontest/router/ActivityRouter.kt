package com.takeiteasy.vip.uklontest.router

import android.content.Context
import android.content.Intent
import com.takeiteasy.vip.uklontest.comments.PostCommentsActivity

const val ARG_POST_ID: String = "ARG_POST_ID"
const val ARG_USER_ID: String = "ARG_USER_ID"

class ActivityRouter {

    fun requestPostCommentsActivity(context: Context, postId: Int, userId: Int, listener: OnIntentReadyListener) {
        val intent = Intent(context, PostCommentsActivity::class.java)
        intent.putExtra(ARG_POST_ID, postId)
        intent.putExtra(ARG_USER_ID, userId)
        listener.onIntentReady(intent)
    }

    interface OnIntentReadyListener {
        fun onIntentReady(intent: Intent)
    }
}