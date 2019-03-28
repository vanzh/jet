package com.vanzh.jet.base.listpage

import android.arch.paging.PageKeyedDataSource

/**
 * created by zp on 2019/3/28.
 */

class PageDataSource<T>(private val dataLoader: PagedDataLoader<T>) : PageKeyedDataSource<Int, T>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, T>) {
        dataLoader.loadInit(params, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        dataLoader.loadAfter(params, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {

    }

}