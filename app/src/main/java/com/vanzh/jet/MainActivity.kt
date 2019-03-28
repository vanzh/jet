package com.vanzh.jet

import android.content.Intent
import com.vanzh.base.BaseActivity
import com.vanzh.jet.ui.ArticleListActivity
import com.vanzh.jet.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun init() {
        showToolBarBack = false
        tvClick.setOnClickListener {
            startActivity(Intent(this, ArticleListActivity::class.java))
        }
    }

    lateinit var mainViewModel: MainViewModel

    override fun bindViewModel() {
        mainViewModel = MainViewModel()

    }

    override fun getLayoutId(): Int = R.layout.activity_main


}
