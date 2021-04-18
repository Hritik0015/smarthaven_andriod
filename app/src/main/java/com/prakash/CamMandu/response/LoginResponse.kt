package com.prakash.CamMandu.response


import com.prakash.CamMandu.entity.User

data class LoginResponse(
    val success :Boolean? = null,
    val token : String? = null,
    val data: User? = null
)
