package com.hugo.excise.jktime.kotlinprimer.playerviewtype

import javax.swing.JOptionPane

/**
 * @author hugo
 * @date 2022-12-10 17:05
 */
interface PlayerView {
    fun showView()

    fun getPlayButton()
}

class GreenPlayerView : PlayerView {
    override fun getPlayButton() {
        println("显示绿色button")
    }

    override fun showView() {
        JOptionPane.showConfirmDialog(null, "显示绿色播放器", "绿色播放器", JOptionPane.DEFAULT_OPTION)
    }
}

class MediaPlayerView(playerView: PlayerView) : PlayerView by playerView