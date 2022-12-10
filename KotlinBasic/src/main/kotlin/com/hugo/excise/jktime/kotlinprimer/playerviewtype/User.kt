package com.hugo.excise.jktime.kotlinprimer.playerviewtype

/**
 * @author hugo
 * @date 2022-12-10 17:01
 */
data class User(var id: Int,
                var name: String,
                var playerType: PlayerViewType = PlayerViewType.BLUE)
