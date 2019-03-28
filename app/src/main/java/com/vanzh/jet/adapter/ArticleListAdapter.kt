package com.vanzh.jet.adapter

import com.vanzh.jet.R
import com.vanzh.jet.base.adapter.BasePagedAdapter
import com.vanzh.jet.base.adapter.ViewHolder
import com.vanzh.jet.model.Article

/**
 * created by zp on 2019/3/28.
 */
class ArticleListAdapter : BasePagedAdapter<Article>(R.layout.wechat_item_common_article) {
    override fun bindViewHolder(holder: ViewHolder, item: Article, position: Int) {

        holder.setText(R.id.tvTitle, item.title)
            .setText(R.id.tvAuthor, item.author)
            .setImageResource(R.id.ivFav, if (item.collect) R.drawable.ic_fav_1 else R.drawable.ic_fav_0)
            .setClickListener(R.id.ivFav) {
                itemClickListener?.apply { this(it, item, position) }
            }
    }

}