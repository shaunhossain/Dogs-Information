package com.shaunhossain.dogsinformation.network

import com.shaunhossain.dogsinformation.model.dogs.DogsItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/wp-json/wp/v2/posts")
    suspend fun getArticles(
        @Query("_fields")
        fields: String = "id,date,title,content,categories,title,featured_media,_links,_embedded",
        @Query("_embed")
        embed: Boolean = true,
        @Query("per_page")
        page: Int
    ): List<DogsItem>
}