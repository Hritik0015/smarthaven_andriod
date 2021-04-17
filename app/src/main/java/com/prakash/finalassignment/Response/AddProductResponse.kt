package com.prakash.finalassignment.Response

import com.prakash.finalassignment.entity.Product


data class AddProductResponse(
    val success: Boolean? = null,
    val data: Product? = null
)