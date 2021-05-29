package com.shaunhossain.dogsinformation.network

import com.shaunhossain.dogsinformation.model.dogs.DogsItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/images/search")
    suspend fun getAllDogs(
        @Query("page") page:Int,
        @Query("limit") limit:Int
    ):List<DogsItem>
}