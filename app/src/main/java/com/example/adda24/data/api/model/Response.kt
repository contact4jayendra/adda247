package com.example.adda24.data.api.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Response(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: ArrayList<Data>?= null,
    @SerializedName("meta")
    val meta: Meta
) : java.io.Serializable

@Keep
data class Data(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("updated_at")
    val updatedAt: String
) : java.io.Serializable

@Keep
data class Meta(
    @SerializedName("pagination")
    val pagination: Pagination
) : java.io.Serializable

@Keep
data class Pagination(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("total")
    val total: Int
) : java.io.Serializable