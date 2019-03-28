package com.vanzh.jet.base.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.Toast

/**
 * created by zp on 2019/3/27.
 */


fun <VH : RecyclerView.ViewHolder, A : RecyclerView.Adapter<VH>> RecyclerView.init(
    adapter: A,
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
) {
    this.layoutManager = layoutManager
    this.adapter = adapter
}


fun View.visibility(visible: Boolean) {
    if (visible) {
        visibility = View.VISIBLE
    } else
        visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.toast(msg: String?) {
    if (!TextUtils.isEmpty(msg)) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}