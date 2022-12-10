package com.hugo.excise.jktime.kotlinprimer.playerviewtype

import javax.swing.JOptionPane

/**
 * @author hugo
 * @date 2022-12-10 17:07
 */
class BluePlayerView : PlayerView {

    override fun getPlayButton() {
        println("显示蓝色button")
    }

    override fun showView() {
        JOptionPane.showConfirmDialog(null, "显示蓝色播放器", "蓝色播放器", JOptionPane.DEFAULT_OPTION);
    }
}