package com.prakash.onlineshopping.api

import com.prakash.onlineshopping.entity.User
import com.prakash.onlineshopping.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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