package com.vanzh.jet.base.view

import android.view.View

/**
 * created by zp on 2019/3/27.
 */

fun View.visibility(visible: Boolean) {
    if (visible) {
        visibility = View.VISIBLE
    } else
        visibility = View.GONE
}