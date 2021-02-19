package com.prakash.onlineshopping.repository

import com.prakash.onlineshopping.api.MyAPIRequest
import com.prakash.onlineshopping.api.ServiceBuilder
import com.prakash.onlineshopping.api.UserAPI
import com.prakash.onlineshopping.entity.User
import com.prakash.onlineshopping.response.LoginResponse


class UserRepository : MyAPIRequest() {
    val myAPI =
        ServiceBuilder.buildService(UserAPI::class.java)

    suspend fun registerUser(user: User): LoginResponse {
        return apiRequest {
            myAPI.registerUser(user)
        }
    }

    suspend fun checkUser(username: String, password: String): LoginResponse {
        return apiRequest {
            myAPI.checkUser(username, password)
        }
    }
}
