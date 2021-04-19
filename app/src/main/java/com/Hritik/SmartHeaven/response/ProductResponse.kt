package com.Hritik.SmartHeaven.response


import com.Hritik.SmartHeaven.entity.Product

data class ProductResponse(
        val success : Boolean? = null,
        val data: MutableList<Product>? = null
)
