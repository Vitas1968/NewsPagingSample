package com.vitaly.newspagingsample.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.vitaly.newspagingsample.api.NetworkService
import com.vitaly.newspagingsample.entity.News
import io.reactivex.rxjava3.disposables.CompositeDisposable

class NewsDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService
)
    : DataSource.Factory<Int, News>() {
    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, News> {
        val newsDataSource = NewsDataSource(networkService, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}