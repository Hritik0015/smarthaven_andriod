package com.prakash.CamMandu.response


import com.prakash.CamMandu.entity.Product

data class ProductResponse(
        val success : Boolean? = null,
        val data: MutableList<Product>? = null
)
