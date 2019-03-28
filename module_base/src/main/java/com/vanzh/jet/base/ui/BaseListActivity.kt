package com.vanzh.jet.base.ui

import android.arch.lifecycle.ViewModelProviders
import android.view.View
import com.vanzh.base.BaseActivity
import com.vanzh.base.R
import com.vanzh.jet.base.adapter.BaseItem
import com.vanzh.jet.base.adapter.BasePagedAdapter
import com.vanzh.jet.base.common.RefreshResult
import com.vanzh.jet.base.view.EmptyView
import com.vanzh.jet.base.view.init
import com.vanzh.jet.base.view.toast
import com.vanzh.jet.base.viewmodel.BaseListViewModel
import kotlinx.android.synthetic.main.activity_base_list.*
import java.lang.reflect.ParameterizedType

/**
 * created by zp on 2019/3/28.
 */

abstract class BaseListActivity<T : BaseItem, VM : BaseListViewModel<T>> : BaseActivity() {


    override fun getLayoutId(): Int = R.layout.activity_base_list

    val viewModel: VM by lazy {
        val p = javaClass.genericSuperclass as ParameterizedType
        val cls = p.actualTypeArguments[1] as Class<VM>
        ViewModelProviders.of(this).get(cls)
    }

    lateinit var mAdapter: BasePagedAdapter<T>

    override fun bindViewModel() {

    }

    override fun init() {
        mAdapter = createAdapter()
        viewModel.observeDataObserver(this,
            {
                mAdapter.submitList(it)
            },
            { refreshFinished(it) },
            { loadMoreFinished(it) })


        viewModel.observeAdapterObserver(this, { position, payload ->
            mAdapter.notifyItemChanged(position, payload)
        }, {
            mAdapter.notifyItemRemoved(it)
        })


    }

    override fun initView() {
        super.initView()
        mAdapter.itemClickListener = { view, item, position -> itemClicked(view, item, position) }

        baseRecyclerView.init(mAdapter)
        baseRefreshLayout.setOnRefreshListener {
            viewModel.init()
        }
    }


    abstract fun createAdapter(): BasePagedAdapter<T>
    open fun itemClicked(view: View, item: T, position: Int) {}

    fun refreshFinished(result: RefreshResult) {
        baseRefreshLayout?.isRefreshing = false
        baseEmptyView?.apply {
            when (result) {
                RefreshResult.FAILED -> state = EmptyView.Status.LOAD_FAILED
                RefreshResult.SUCCEED -> state = EmptyView.Status.DISMISS
                RefreshResult.NO_DATA -> state = EmptyView.Status.NO_DATA
                RefreshResult.NO_MORE -> {
                    state = EmptyView.Status.NO_DATA
                    baseRefreshLayout.toast("全部加载完成")
                }
            }
        }
    }

    fun loadMoreFinished(result: RefreshResult) {
        when (result) {
            RefreshResult.SUCCEED -> {
            }
            RefreshResult.FAILED -> {
            }
            RefreshResult.NO_MORE -> baseRefreshLayout.toast("全部加载完成")
        }
    }


}