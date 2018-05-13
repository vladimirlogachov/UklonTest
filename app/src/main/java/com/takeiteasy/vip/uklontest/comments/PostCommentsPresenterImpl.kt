package com.takeiteasy.vip.uklontest.comments

import android.util.Log
import com.takeiteasy.vip.uklontest.api.Api
import com.takeiteasy.vip.uklontest.models.User
import com.takeiteasy.vip.uklontest.models.comments.PostComment
import com.takeiteasy.vip.uklontest.storage.Storage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PostCommentsPresenterImpl(
        private val api: Api,
        private val disposable: CompositeDisposable,
        private val storage: Storage
) : PostCommentsContract.PostCommentsPresenter {

    var view: PostCommentsContract.PostCommentsView? = null

    override fun setPostCommentsView(view: PostCommentsContract.PostCommentsView?) {
        this.view = view

        if (this.view == null) {
            disposable.clear()
        }
    }

    override fun refresh(postId: Int) {
        storage.removePostComments(postId)
        loadComments(postId)
    }

    override fun loadUserInfo(userId: Int) {
        disposable.add(
                api.loadUserInfo(userId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<User>() {
                            override fun onError(e: Throwable) {
                                view?.showError(e.localizedMessage)
                            }

                            override fun onSuccess(t: User) {
                                view?.showUserInfo(t)
                            }

                        })
        )
    }

    override fun loadComments(postId: Int) {
        if (storage.containsPostComments(postId)) {
            view?.showPostComments(storage.loadPostComments(postId))
            Log.d("TAG", "loadComments - " + postId + " from storage ? " + storage.containsPostComments(postId))
            return
        }

        disposable.add(
                api.loadPostComments(postId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<PostComment>>() {
                            override fun onStart() {
                                super.onStart()

                                view?.showProgress(true)
                            }

                            override fun onSuccess(t: List<PostComment>) {
                                view?.showProgress(false)
                                storage.savePostComments(postId, t)
                                view?.showPostComments(storage.loadPostComments(postId))
                            }

                            override fun onError(e: Throwable) {
                                view?.showProgress(false)
                                view?.showError(e.localizedMessage)
                            }

                        })
        )
    }
}