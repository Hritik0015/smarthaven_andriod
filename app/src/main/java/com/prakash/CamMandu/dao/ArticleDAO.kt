package com.prakash.CamMandu.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prakash.CamMandu.entity.Article

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Query("SELECT * FROM Article")
    suspend fun getAllArticles():MutableList<Article>
}