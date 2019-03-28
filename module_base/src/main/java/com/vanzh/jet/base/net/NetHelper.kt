package com.vanzh.jet.base.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * created by zp on 2019/3/28.
 */

object NetHelper {
    val INSTANCE: Retrofit = createRetrofit()


    private fun createRetrofit(): Retrofit = Retrofit.Builder().client(createOKHttp())
        .baseUrl(baseurl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun createOKHttp(): OkHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .build()

    private val baseurl: String = "https://www.wanandroid.com"
}