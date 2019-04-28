package com.vanzh.jet.ui

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.vanzh.jet.R
import com.vanzh.jet.adapter.ArticleListAdapter
import com.vanzh.jet.base.RouterConst
import com.vanzh.jet.base.adapter.BasePagedAdapter
import com.vanzh.jet.base.ui.BaseListActivity
import com.vanzh.jet.base.viewmodel.BaseListViewModel
import com.vanzh.jet.model.Article
import com.vanzh.jet.viewmodel.ArticleListModel

@Route(path = RouterConst.articleListActivity)
class ArticleListActivity : BaseListActivity<Article, ArticleListModel>() {
    override fun createViewModel(): BaseListViewModel<Article> = ArticleListModel()

    override fun createAdapter(): BasePagedAdapter<Article> = ArticleListAdapter()

    override fun itemClicked(view: View, item: Article, position: Int) {
        super.itemClicked(view, item, position)
        if (view.id == R.id.ivFav) {
            viewModel.collect(item, position)
        } else {

                ARouter.getInstance().build(com.vanzh.jet.base.common.RouterConst.webActivity)
                    .withString("title", item.title)
                    .withString("url", item.link)
                    .navigation()


        }
    }
}


