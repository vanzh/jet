package com.vanzh.jet.net

import com.vanzh.jet.base.net.HttpRest
import com.vanzh.jet.base.net.ListPaged
import com.vanzh.jet.base.net.NetHelper
import com.vanzh.jet.model.Article
import com.vanzh.jet.model.ArticleSource
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * created by zp on 2019/3/28.
 */

object Api : Service by NetHelper.INSTANCE.create(Service::class.java)

interface Service {

    @GET("wxarticle/chapters/json")
    fun getArticleSoruce(): Observable<HttpRest<List<ArticleSource>>>

    @GET("wxarticle/list/{authorId}/{page}/json")
    fun getWechatArticles(
        @Path("authorId") authorId: Int = 409,
        @Path("page") page: Int
    ): Observable<HttpRest<ListPaged<Article>>>

}