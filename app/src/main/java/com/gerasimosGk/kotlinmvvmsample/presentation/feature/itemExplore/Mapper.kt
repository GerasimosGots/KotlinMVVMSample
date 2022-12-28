package com.gerasimosGk.kotlinmvvmsample.presentation.feature.itemExplore

import com.gerasimosGk.kotlinmvvmsample.domain.UserModel

/**
 * Created by Gerasimos on 6/12/2021
 *
 * Kotlin file with an extension functions
 * This is a mapper function, converts a collection of UIUserListModel from a collection of UserModel
 */

/**
 * Extension function that converts UIUserListModel to UIUserListModel
 *
 * @param MutableList<UserModel>
 * @return MutableList<UIUserListModel>
 */
fun MutableList<UserModel>.toUserListModel(): MutableList<UIUserListModel> {
    val listUI: MutableList<UIUserListModel> = mutableListOf()
    this.map {
        val userListMode = UIUserListModel(
            id = it.id,
            description = it.userName,
            coverImage = it.photoUrl
        )
        listUI.add(userListMode)
    }
    return listUI
}