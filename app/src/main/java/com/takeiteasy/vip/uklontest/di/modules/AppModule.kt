package com.takeiteasy.vip.uklontest.di.modules

import com.takeiteasy.vip.uklontest.api.Api
import com.takeiteasy.vip.uklontest.comments.PostCommentsContract
import com.takeiteasy.vip.uklontest.comments.PostCommentsPresenterImpl
import com.takeiteasy.vip.uklontest.main.MainContract
import com.takeiteasy.vip.uklontest.main.MainPresenterImpl
import com.takeiteasy.vip.uklontest.router.ActivityRouter
import com.takeiteasy.vip.uklontest.storage.Storage
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideStorage(): Storage {
        return Storage()
    }

    @Provides
    @Singleton
    fun provideActivityRouter(): ActivityRouter {
        return ActivityRouter()
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    fun provideMainPresenter(api: Api, disposable: CompositeDisposable, storage: Storage): MainContract.MainPresenter {
        return MainPresenterImpl(api, disposable, storage)
    }

    @Provides
    @Singleton
    fun providePostCommentsPresenter(api: Api, disposable: CompositeDisposable, storage: Storage): PostCommentsContract.PostCommentsPresenter {
        return PostCommentsPresenterImpl(api, disposable, storage)
    }
}