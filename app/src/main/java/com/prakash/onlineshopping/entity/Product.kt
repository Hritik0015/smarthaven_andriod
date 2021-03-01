package com.prakash.onlineshopping.entity

import androidx.room.Entity

@Entity
data class Product (
        var name: String? = null,
        var qty: String? = null,
        var specification: String? = null,
        var prdimage: String? = null,
