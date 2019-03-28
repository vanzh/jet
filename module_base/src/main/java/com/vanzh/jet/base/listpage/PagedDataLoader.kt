package com.vanzh.jet.base.listpage

import android.arch.paging.PageKeyedDataSource

/**
 * created by zp on 2019/3/28.
 */

interface PagedDataLoader<T> {
    fun loadInit(
        param: PageKeyedDataSource.LoadInitialParams<Int>,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, T>
    )

    fun loadAfter(param: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, T>)
    fun refresh()
    fun loadMore()
}