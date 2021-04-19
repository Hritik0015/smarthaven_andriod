package com.Hritik.wearables.response

import com.Hritik.wearables.entity.User

data class LoginResponse(
    val success :Boolean? = null,
    val token : String? = null,
    val data: MutableList<User>? = null
)
