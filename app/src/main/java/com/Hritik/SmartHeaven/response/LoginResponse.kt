package com.Hritik.SmartHeaven.response


import com.Hritik.SmartHeaven.entity.User

data class LoginResponse(
    val success :Boolean? = null,
    val token : String? = null,
    val data: User? = null
)
