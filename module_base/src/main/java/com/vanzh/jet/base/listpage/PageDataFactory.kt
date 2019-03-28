package com.vanzh.jet.base.listpage

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource


/**
 * created by zp on 2019/3/28.
 */

class PageDataFactory<T>(private val dataLoader: PagedDataLoader<T>) : DataSource.Factory<Int, T>() {
    val sourceLiveData = MutableLiveData<PageDataSource<T>>()

    override fun create(): DataSource<Int, T> {
        val pageDataSource = PageDataSource(dataLoader)
        sourceLiveData.postValue(pageDataSource)
        return pageDataSource
    }

}