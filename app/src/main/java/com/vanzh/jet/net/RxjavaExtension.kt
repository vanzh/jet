package com.vanzh.jet.net

import com.vanzh.jet.base.net.HttpRest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 扩展rxjava 请求
 * created by zp on 2019/3/28.
 */

fun <D, T : HttpRest<D>> Observable<T>.io_main() =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


fun <D, T : HttpRest<D>> Observable<T>.subscribeBy(onResponse: (D?) -> Unit, onFailure: (String) -> Unit) =
    subscribe({
        if (it.errorCode == 0) {
            onResponse(it.data)
        } else {
            onFailure(it.errorMsg)
        }
    }, { onFailure(it.message!!) })

