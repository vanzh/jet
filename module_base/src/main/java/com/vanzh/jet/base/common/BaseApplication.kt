package com.vanzh.jet.base.common

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * created by zp on 2019/4/28.
 */
class BaseApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}