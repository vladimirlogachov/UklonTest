package com.takeiteasy.vip.uklontest.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.takeiteasy.vip.uklontest.R
import com.takeiteasy.vip.uklontest.models.posts.Post
import com.takeiteasy.vip.uklontest.router.ActivityRouter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.MainView, SwipeRefreshLayout.OnRefreshListener, ActivityRouter.OnIntentReadyListener {

    private lateinit var adapter: PostsAdapter

    @Inject
    lateinit var presenter: MainContract.MainPresenter
    @Inject
    lateinit var router: ActivityRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        adapter = PostsAdapter { postId: Int, userId: Int ->
            router.requestPostCommentsActivity(this, postId, userId, this)
        }

        postsRecycler.adapter = adapter
        postsRecycler.layoutManager = LinearLayoutManager(this)
        postsRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        postsRefresher.setOnRefreshListener(this)

        presenter.setMainView(this)
        presenter.loadPosts()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.setMainView(null)
    }

    override fun showProgress(show: Boolean) {
        postsRefresher.isRefreshing = show
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showPosts(posts: List<Post>) {
        adapter.updateData(posts)
    }

    override fun onRefresh() {
        presenter.refresh()
    }

    override fun onIntentReady(intent: Intent) {
        startActivity(intent)
    }
}
