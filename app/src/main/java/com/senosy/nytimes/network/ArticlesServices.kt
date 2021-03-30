package com.senosy.nytimes.network

import com.senosy.nytimes.network.models.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val apiServices: ArticlesServices by lazy {
    retrofit.create(ArticlesServices::class.java)
}

interface ArticlesServices {

    @GET("viewed/{period}.json")
    suspend fun getArticle(
        @Path("period") period: Int? = 1,
    ): ArticleResponse


}