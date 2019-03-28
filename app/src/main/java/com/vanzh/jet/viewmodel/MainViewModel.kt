package com.vanzh.jet.viewmodel

import com.socks.library.KLog
import com.vanzh.jet.base.viewmodel.BaseViewModel
import com.vanzh.jet.net.Api
import com.vanzh.jet.net.io_main
import com.vanzh.jet.net.subscribeBy

/**
 * created by zp on 2019/3/28.
 */
class MainViewModel : BaseViewModel() {

    fun getArticleSoruce() {
        Api.getArticleSoruce().io_main().subscribeBy(onResponse = {
            KLog.d(TAG, it.toString())
        }, onFailure = {
            KLog.d(TAG, it)
        }).add()
    }
}