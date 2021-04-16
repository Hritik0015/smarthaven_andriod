package com.prakash.finalassignment.Repository

import com.prakash.finalassignment.Response.LoginResponse
import com.prakash.finalassignment.api.MyAPIRequest
import com.prakash.finalassignment.api.ServiceBuilder
import com.prakash.finalassignment.api.UserAPI
import com.prakash.finalassignment.entity.User

class UserRepository : MyAPIRequest(){

    private val myApi =
            ServiceBuilder.buildService(UserAPI::class.java)

    suspend fun checkUser(username : String,password :String) : LoginResponse{
        return apiRequest {
            myApi.checkUser(username,password)
        }
    }

    suspend fun registerUser(user : User) : LoginResponse{
        return apiRequest {
            myApi.registerUser(user)
        }
    }
}