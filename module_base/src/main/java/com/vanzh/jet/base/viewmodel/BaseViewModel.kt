package com.vanzh.jet.base.viewmodel

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * created by zp on 2019/3/28.
 */


open class BaseViewModel : ViewModel(), LifecycleObserver {
    final val TAG:String ="${javaClass.simpleName}"

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }


    open fun Disposable.add() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }


}