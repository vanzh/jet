package com.vanzh.jet.base.ui

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.vanzh.base.R
import com.vanzh.jet.base.view.ActionViewLayout
import com.vanzh.jet.base.view.visibility
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.layout_action_view.view.*

/**
 * created by zp on 2019/3/27.
 */

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(generateLayout())
        ARouter.getInstance().inject(this)

        if (showActionBar()) createActionBar()

        if (getLayoutId() != 0) createContent()

        bindViewModel()
        init()
        initView()

    }


    final fun generateLayout(): View {
        return View.inflate(this, R.layout.activity_base, null) as LinearLayout
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    open fun showActionBar() = true

    lateinit var toolBar: ActionViewLayout

    open fun createActionBar() {
        svActionBar.layoutResource = R.layout.toolbar
        toolBar = svActionBar.inflate() as ActionViewLayout
        toolBar.toolbarBack.visibility(!showToolBarBack)
        toolBar.toolbarBack.setOnClickListener {
            finish()
        }
        toolBar.toolbarTitle.text = this.title
    }

    open fun setTitleBarTitle(title: String) {
        toolBar.toolbarTitle.text = title
    }

    var showToolBarBack: Boolean = true
        set(value) {
            field = value
            toolBar?.toolbarBack?.visibility(value)
        }

    fun createContent() {
        svContent.layoutResource = getLayoutId()
        svContent.inflate()
    }

    abstract fun bindViewModel()

    abstract fun init()

    open fun initView() {}
}