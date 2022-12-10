package com.hugo.excise.jktime.kotlinprimer.playerviewtype

/**
 * @author hugo
 * @date 2022-12-10 17:06
 * 音乐播放APP，每个用户都可以定制自己的播放器皮肤颜色
 */
class PlayerUI private constructor() {
    companion object {
        fun get(): PlayerUI {
            return Holder.instance
        }
    }

    private object Holder {
        val instance = PlayerUI()
    }

    fun showPlayer(user: User) {
        MediaPlayerView(getPlayerView(user.playerType)).showView()
    }
}