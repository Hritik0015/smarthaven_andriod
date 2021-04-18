package com.prakash.wearables.repository

import com.prakash.wearables.api.MyApiRequest
import com.prakash.wearables.api.ServiceBuilder
import com.prakash.wearables.api.UserAPI
import com.prakash.wearables.entity.User
import com.prakash.wearables.response.LoginResponse

class UserRepository : MyApiRequest() {
    private val userAPI = ServiceBuilder.buildService(UserAPI::class.java)

    suspend fun registerUser(user: User): LoginResponse {
        return apiRequest {
            userAPI.registerUser(user)
        }
    }

    suspend fun checkUser(user_email: String, user_password: String):LoginResponse{
        return apiRequest {
            userAPI.checkUser(user_email, user_password)
        }
    }

    //Retrieve User
    suspend fun userDetails(token:String,userToken:String): LoginResponse {
        return apiRequest {
            userAPI.retrieveUser(ServiceBuilder.token!!,userToken)
        }
    }
}