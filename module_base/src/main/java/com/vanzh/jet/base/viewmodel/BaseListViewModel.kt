package com.vanzh.jet.base.viewmodel

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import android.nfc.tech.MifareUltralight
import android.support.annotation.NonNull
import com.vanzh.jet.base.common.RefreshResult
import com.vanzh.jet.base.listpage.PageDataFactory
import com.vanzh.jet.base.listpage.PagedDataLoader

/**
 * created by zp on 2019/3/28.
 */

abstract class BaseListViewModel<T> : BaseViewModel(), PagedDataLoader<T> {


    open fun pageSize(): Int = 10

    private val pageDatatypeFactory: PageDataFactory<T> by lazy {
        PageDataFactory(this)
    }

    private val loadLiveData = LivePagedListBuilder(pageDatatypeFactory, pageSize()).build()

    private val refreshLiveData: MutableLiveData<RefreshResult> = MutableLiveData()

    private val loadMoreLiveData: MutableLiveData<RefreshResult> by lazy { MutableLiveData<RefreshResult>() }

    private val notifyItemLiveData: MutableLiveData<Pair<Int, Any?>> by lazy { MutableLiveData<Pair<Int, Any?>>() }

    private val removeItemLiveData: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    fun invalidate() {
        pageDatatypeFactory.sourceLiveData.value?.invalidate()
    }

    override fun loadInit(
        param: PageKeyedDataSource.LoadInitialParams<Int>,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, T>
    ) {
        refresh()
        loadData(1) {
            when {
                it == null -> refreshLiveData.value = RefreshResult.FAILED

                it.isEmpty() -> refreshLiveData.value = RefreshResult.NO_DATA

                it.size < MifareUltralight.PAGE_SIZE -> {
                    callback.onResult(it, null, null)
                    refreshLiveData.value = RefreshResult.NO_MORE
                }

                else -> {
                    callback.onResult(it, null, 2)
                    refreshLiveData.value = RefreshResult.SUCCEED
                }
            }
        }
    }

    override fun loadAfter(
        param: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, T>
    ) {
        loadMore()

        loadData(param.key) {
            when {
                it == null -> loadMoreLiveData.value = RefreshResult.FAILED
                it.size < pageSize() -> {
                    callback.onResult(it, null)
                    loadMoreLiveData.value = RefreshResult.NO_MORE
                }
                else -> {
                    callback.onResult(it, param.key + 1)
                    loadMoreLiveData.value = RefreshResult.SUCCEED
                }
            }
        }
    }

    override fun refresh() {
    }

    override fun loadMore() {
    }

    //观察数据来源
    fun observeDataObserver(
        @NonNull owner: LifecycleOwner,
        dataCallBack: (PagedList<T>) -> Unit,
        refreshResult: (RefreshResult) -> Unit,
        loadMoreResult: (RefreshResult) -> Unit
    ) {
        loadLiveData.observe(owner, Observer {
            it?.apply(dataCallBack)
        })

        refreshLiveData.observe(owner, Observer {
            refreshResult(it!!)
        })

        loadMoreLiveData.observe(owner, Observer {
            loadMoreResult(it!!)
        })
    }

    fun observeAdapterObserver(
        @NonNull owner: LifecycleOwner,
        notifyItemCallBack: (Int, Any?) -> Unit,
        removeItemCallBack: (Int) -> Unit
    ) {
        notifyItemLiveData.observe(owner, Observer {
            it?.apply { notifyItemCallBack(first, second) }
        })

        removeItemLiveData.observe(owner, Observer {
            it?.apply { removeItemCallBack(it) }
        })
    }

    abstract fun loadData(page: Int, onResponse: (ArrayList<T>?) -> Unit)

    fun notifyItem(position: Int, payload: Any?) {
        notifyItemLiveData.value = Pair(position, payload)
    }

    fun removeItem(position: Int) {

    }


}