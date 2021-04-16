package com.prakash.finalassignment.api

import com.prakash.finalassignment.Response.LoginResponse
import com.prakash.finalassignment.entity.User
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {
    @POST("insert")
    suspend fun registerUser(
            @Body user: User
    ):Response<LoginResponse>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkUser(
            @Field("username") username: String,
            @Field("password") password: String
    ):Response<LoginResponse>

}