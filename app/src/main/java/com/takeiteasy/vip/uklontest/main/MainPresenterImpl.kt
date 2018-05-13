package com.takeiteasy.vip.uklontest.main

import com.takeiteasy.vip.uklontest.api.Api
import com.takeiteasy.vip.uklontest.models.posts.RegularPost
import com.takeiteasy.vip.uklontest.storage.Storage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainPresenterImpl (
        private val api: Api,
        private val disposable: CompositeDisposable,
        private val storage: Storage
) : MainContract.MainPresenter {

    var view: MainContract.MainView? = null

    override fun refresh() {
        storage.removeRegularPosts()
        loadPosts()
    }

    override fun setMainView(view: MainContract.MainView?) {
        this.view = view

        if (this.view == null) {
            disposable.clear()
        }
    }

    override fun loadPosts() {
        if (storage.containsRegularPosts()) {
            view?.showPosts(storage.loadRegularPosts())
            return
        }

        disposable.addAll(
                api.loadPosts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<RegularPost>>() {
                            override fun onStart() {
                                super.onStart()

                                view?.showProgress(true)
                            }

                            override fun onSuccess(t: List<RegularPost>) {
                                view?.showProgress(false)
                                storage.saveRegularPosts(t)
                                view?.showPosts(t)
                            }

                            override fun onError(e: Throwable) {

                                view?.showProgress(false)
                                view?.showError(e.localizedMessage)
                            }
                        })
        )
    }
}