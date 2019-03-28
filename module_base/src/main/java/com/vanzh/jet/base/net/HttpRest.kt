package com.vanzh.jet.base.net

import java.io.Serializable

/**
 * created by zp on 2019/3/28.
 */

data class HttpRest<T>(val data: T, val errorCode: Int, val errorMsg: String) : Serializable