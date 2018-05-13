package com.takeiteasy.vip.uklontest.comments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.takeiteasy.vip.uklontest.R
import com.takeiteasy.vip.uklontest.models.User
import com.takeiteasy.vip.uklontest.models.comments.Comment
import com.takeiteasy.vip.uklontest.router.ARG_POST_ID
import com.takeiteasy.vip.uklontest.router.ARG_USER_ID
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_post_comments.*
import kotlinx.android.synthetic.main.content_post_comments.*
import kotlinx.android.synthetic.main.content_user_info.*
import javax.inject.Inject

class PostCommentsActivity : AppCompatActivity(), PostCommentsContract.PostCommentsView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: PostCommentsContract.PostCommentsPresenter

    private lateinit var adapter: CommentsAdapter
    private var postId: Int = -1
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_comments)
        setSupportActionBar(toolbar)

        postId = intent.getIntExtra(ARG_POST_ID, -1)
        userId = intent.getIntExtra(ARG_USER_ID, -1)

        adapter = CommentsAdapter()
        commentsRecycler.adapter = adapter;
        commentsRecycler.layoutManager = LinearLayoutManager(this)
        commentsRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        commentsRefresher.setOnRefreshListener(this)

        presenter.setPostCommentsView(this)
        presenter.loadUserInfo(userId)
        presenter.loadComments(postId)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.setPostCommentsView(null)
    }

    override fun showProgress(show: Boolean) {
        commentsRefresher.isRefreshing = show
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showPostComments(posts: List<Comment>) {
        adapter.updateData(posts)
    }

    override fun onRefresh() {
        presenter.refresh(postId)
    }

    override fun showUserInfo(user: User) {
        userRealName.text = user.name
        userNickname.text = user.username
        userEmail.text = user.email
        userPhone.text = user.phone
        userWebsite.text = user.website
        // and so on
    }
}
