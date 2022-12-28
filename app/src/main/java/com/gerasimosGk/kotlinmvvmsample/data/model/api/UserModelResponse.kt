package com.gerasimosGk.kotlinmvvmsample.data.model.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModelResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("username")
    val userName: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("phone")
    val phone: String
) : Parcelable