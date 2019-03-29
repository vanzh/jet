package com.vanzh.jet.viewmodel

import com.vanzh.jet.base.viewmodel.BaseListViewModel
import com.vanzh.jet.model.Article
import com.vanzh.jet.net.Api
import com.vanzh.jet.net.io_main
import com.vanzh.jet.net.subscribeBy

/**
 * created by zp on 2019/3/28.
 */
class ArticleListModel : BaseListViewModel<Article>() {

    override fun loadData(page: Int, onResponse: (ArrayList<Article>?) -> Unit) {

        Api.getWechatArticles(page = page)
            .io_main()
            .subscribeBy(onResponse = {
                onResponse(it?.datas)
            }, onFailure = {
                onResponse(null)
            }).add()
    }


    fun collect(item: Article, position: Int) {
        item.collect = !item.collect
        notifyItem(position, item)
    }
}