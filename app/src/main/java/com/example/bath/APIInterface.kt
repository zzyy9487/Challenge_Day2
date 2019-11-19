package com.example.bath

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {

    @Headers("Content-Type: application/json","Accept: application/json")
    @GET("/api/admin")
    fun getList(
    ): Call<Data>

    @POST("/api/admin")
    fun byebye(
        @Body kick:Kick
    ): Call<Data2>

}