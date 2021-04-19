package com.Hritik.SmartHeaven.api

import com.Hritik.SmartHeaven.response.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SingleArticleAPI {
    //Single article show
    @GET("/article/singleshow/{id}")
    suspend fun showSingleArticle(
            @Path("id")id:String
    ): Response<ArticleResponse>
}