package com.takehomechallenge.aliftrd.data.lib

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("info")
    val info: Info,

    @SerializedName("results")
    val results: T?,
)

data class Info(
    @field:SerializedName("next")
    val next: String,

    @field:SerializedName("pages")
    val pages: Int,

    @field:SerializedName("prev")
    val prev: Any,

    @field:SerializedName("count")
    val count: Int
)
