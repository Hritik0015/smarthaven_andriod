package com.Hritik.SmartHeaven.response

import com.Hritik.SmartHeaven.entity.Article

data class ArticleResponse(
        val success : Boolean? = null,
        val data: MutableList<Article>? = null
)
