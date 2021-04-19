package com.Hritik.SmartHeaven.response

import com.Hritik.SmartHeaven.entity.Cart

data class CartResponse (
    val success: Boolean? = null,
    val data: MutableList<Cart>? = null
)