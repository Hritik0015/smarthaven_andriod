package com.prakash.CamMandu.response

import com.prakash.CamMandu.entity.Article

data class ArticleResponse(
        val success : Boolean? = null,
        val data: MutableList<Article>? = null
)
