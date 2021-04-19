package com.Hritik.SmartHeaven.repository

import com.Hritik.SmartHeaven.api.MyApiRequest
import com.Hritik.SmartHeaven.api.ServiceBuilder
import com.Hritik.SmartHeaven.api.SingleArticleAPI

import com.Hritik.SmartHeaven.response.ArticleResponse


class SingleArticleRepository: MyApiRequest() {
    private val singleArticleAPI = ServiceBuilder.buildService(SingleArticleAPI::class.java)

    //Display Single Article
    suspend fun getSingleArticle(id:String): ArticleResponse {
        return apiRequest {
            singleArticleAPI.showSingleArticle(id)
        }
    }
}