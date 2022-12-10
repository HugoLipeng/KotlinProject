package com.hugo.excise.jktime.kotlinprimer.playerviewtype

import javax.swing.JOptionPane

/**
 * @author hugo
 * @date 2022-12-10 17:09
 */
val TITLE = "播放器标题"
val MESSAGE = "播放器消息"

class VIPPlayer(var title: String?, var message: String?) : PlayerView {

    init {
        println("主构造方法")
        title = title ?: TITLE
        message = message ?: TITLE
    }

    constructor() : this(TITLE, MESSAGE) {
        println("无参构造方法")
    }

    constructor(message: String?) : this(MESSAGE, message) {
        println("一个参数的参构造方法")
    }

    override fun getPlayButton() {
        println("VIP button")
    }

    override fun showView() {
        JOptionPane.showConfirmDialog(null, message, title, JOptionPane.DEFAULT_OPTION)
    }
}
