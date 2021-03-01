package com.prakash.onlineshopping.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        var firstname: String? = null,
        var lastname: String? = null,
        var username: String? = null,
        var email: String? = null,
        var age: String? = null,
        var password: String? = null,
        val userType:String?=null
)