package com.prakash.finalassignment.Response

import com.prakash.finalassignment.entity.Product

data class GetAllProductResponse(
        val success: Boolean? = null,
        val count: Int? = null,
        val data: MutableList<Product>? = null
)