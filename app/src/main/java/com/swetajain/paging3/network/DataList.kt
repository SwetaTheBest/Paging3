package com.swetajain.paging3.network

import com.google.gson.annotations.SerializedName

data class DataList(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)