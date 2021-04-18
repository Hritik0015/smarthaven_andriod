package com.prakash.CamMandu.response

import com.prakash.CamMandu.entity.Cart

data class CartResponse (
    val success: Boolean? = null,
    val data: MutableList<Cart>? = null
)