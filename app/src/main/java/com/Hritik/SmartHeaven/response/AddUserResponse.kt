package com.Hritik.SmartHeaven.response

import com.Hritik.SmartHeaven.entity.User

data class AddUserResponse(
    val success: Boolean?=null,
    val data : User?= null
)
