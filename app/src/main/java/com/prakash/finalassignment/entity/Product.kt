package com.prakash.finalassignment.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product (
        @PrimaryKey

        val _id: String = "",

        val name: String? = null,

        val qty: String? = null,

        val specification: String? = null,

        val prdimage: String? = null,

        val photo: String? = null,

//        val price:String?=null
)