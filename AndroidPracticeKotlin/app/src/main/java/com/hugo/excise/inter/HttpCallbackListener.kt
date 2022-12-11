package com.hugo.excise.inter

/**
 * @author hugo
 * @date 2022-12-11 22:36
 */
interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e: Exception)
}