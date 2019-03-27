package com.vanzh.jet.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.vanzh.base.R
import kotlinx.android.synthetic.main.layout_action_view.view.*

/**
 * created by zp on 2019/3/27.
 */

class ActionViewLayout : FrameLayout {

    var title: CharSequence? = ""
        set(value) {
            field = value
            toolbarTitle.text = title
        }

    var showBack: Boolean = true
        set(value) {
            field = value
            toolbarBack?.visibility(value)
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }


    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.layout_action_view, this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ActionBarLayout)
            typedArray.apply {
                title = getString(R.styleable.ActionViewLayout_title)
                showBack = getBoolean(R.styleable.ActionViewLayout_showBack, true)
                recycle()
            }

        }
    }

}