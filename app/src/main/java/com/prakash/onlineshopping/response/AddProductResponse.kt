package com.prakash.onlineshopping.response

import com.prakash.onlineshopping.entity.Product


data class AddProductResponse(
        val success: Boolean? = null,
        val data: Product? = null
)