package com.prakash.CamMandu.response

import com.prakash.CamMandu.entity.User

data class AddUserResponse(
    val success: Boolean?=null,
    val data : User?= null
)
