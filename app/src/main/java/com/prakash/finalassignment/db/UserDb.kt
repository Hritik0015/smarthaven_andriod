package com.prakash.finalassignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prakash.finalassignment.dao.ProductDAO
import com.prakash.finalassignment.dao.UserDAO
import com.prakash.finalassignment.entity.Product
import com.prakash.finalassignment.entity.User

@Database(
        entities = [(Product::class), (User::class)],
        version = 2,
        exportSchema = false
)
abstract class UserDb : RoomDatabase() {
    abstract fun getProdcutDAO():ProductDAO
    abstract fun getUserDAO(): UserDAO

    companion object {
        @Volatile
        private var instance: UserDb? = null

        fun getInstance(context: Context): UserDb {
            if (instance == null) {
                synchronized(UserDb::class) {
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        UserDb::class.java,
                        "UserDB"
                ).build()
    }
}