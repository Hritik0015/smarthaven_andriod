package com.Hritik.SmartHeaven.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.Hritik.SmartHeaven.dao.ArticleDAO
import com.Hritik.SmartHeaven.entity.Article


@Database(
        entities = [(Article::class)],
        version = 1,
        exportSchema = false
)
abstract class ArticleDB:RoomDatabase() {
    abstract fun getArticleDAO() : ArticleDAO

    companion object{
        @Volatile
        private var instance : ArticleDB? = null

        fun getInstance(context : Context) : ArticleDB{
            if(instance == null){
                synchronized(ArticleDB::class){
                    instance = buildDatabase(context)
                }
            }

            return instance!!
        }

        private fun buildDatabase(context : Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDB::class.java,
                        "ArticleDatabase"
                ).build()

    }
}