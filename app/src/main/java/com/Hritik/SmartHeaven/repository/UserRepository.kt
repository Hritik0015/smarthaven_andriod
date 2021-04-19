package com.Hritik.SmartHeaven.repository

import com.Hritik.SmartHeaven.api.MyApiRequest
import com.Hritik.SmartHeaven.api.ServiceBuilder
import com.Hritik.SmartHeaven.api.UserAPI
import com.Hritik.SmartHeaven.entity.User
import com.Hritik.SmartHeaven.response.LoginResponse

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