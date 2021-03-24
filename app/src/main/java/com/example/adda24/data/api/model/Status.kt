package com.example.adda24.data.api.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
enum class Status {
    @SerializedName("SUCCESS")
    SUCCESS,

    @SerializedName("ERROR")
    ERROR,

    @SerializedName("LOADING")
    LOADING
}