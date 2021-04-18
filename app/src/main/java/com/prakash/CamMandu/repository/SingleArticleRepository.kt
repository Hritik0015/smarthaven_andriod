package com.prakash.CamMandu.repository

import com.prakash.CamMandu.api.MyApiRequest
import com.prakash.CamMandu.api.ServiceBuilder
import com.prakash.CamMandu.api.SingleArticleAPI

import com.prakash.CamMandu.response.ArticleResponse


class SingleArticleRepository: MyApiRequest() {
    private val singleArticleAPI = ServiceBuilder.buildService(SingleArticleAPI::class.java)

    //Display Single Article
    suspend fun getSingleArticle(id:String): ArticleResponse {
        return apiRequest {
            singleArticleAPI.showSingleArticle(id)
        }
    }
}