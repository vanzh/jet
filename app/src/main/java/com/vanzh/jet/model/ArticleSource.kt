package com.vanzh.jet.model

import com.vanzh.jet.base.adapter.BaseItem

/**
 * created by zp on 2019/3/28.
 */

data class ArticleSource(
    var courseId: Int,
    var name: String,
    var order: Int,
    var parentChapterId: Int,
    var userControlSetTop: Boolean,
    var visible: Int
) : BaseItem()