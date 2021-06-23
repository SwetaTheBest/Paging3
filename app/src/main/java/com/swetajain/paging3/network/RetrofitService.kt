package com.swetajain.paging3.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("character")
    suspend fun getDataFromApi(@Query("page") query: Int): DataList
}