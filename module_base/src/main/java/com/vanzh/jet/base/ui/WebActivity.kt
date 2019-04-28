package com.vanzh.jet.base.ui

import android.webkit.WebChromeClient
import android.webkit.WebView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.vanzh.base.R
import com.vanzh.jet.base.common.RouterConst
import com.vanzh.jet.base.view.gone
import kotlinx.android.synthetic.main.activity_base_web.*

/**
 * created by zp on 2019/4/28.
 */

@Route(path = RouterConst.webActivity)
class WebActivity : BaseActivity(){

    @JvmField
    @Autowired(name = "title")
    var title: String? = null

    @JvmField
    @Autowired(name = "url")
    var url: String? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_base_web
    }

    override fun bindViewModel() {

    }

    override fun init() {
        showToolBarBack = true;
        setTitleBarTitle(title!!)
        wbContent.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                pbWeb?.apply {
                    progress = newProgress
                }
                if(newProgress>=100) pbWeb.gone()

            }
        }
        wbContent.loadUrl(url)
    }

}