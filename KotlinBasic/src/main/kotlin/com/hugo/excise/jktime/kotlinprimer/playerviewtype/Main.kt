package com.hugo.excise.jktime.kotlinprimer.playerviewtype

/**
 * @author hugo
 * @date 2022-12-10 17:08
 */
fun main(args: Array<String>) {
    val user = User(1, "name", PlayerViewType.VIP("VIP播放器","VIP的播放器"))
    PlayerUI.get().showPlayer(user)
}