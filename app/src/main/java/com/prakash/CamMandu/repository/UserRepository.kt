package com.prakash.CamMandu.repository

import com.prakash.CamMandu.api.MyApiRequest
import com.prakash.CamMandu.api.ServiceBuilder
import com.prakash.CamMandu.api.UserAPI
import com.prakash.CamMandu.entity.User
import com.prakash.CamMandu.response.LoginResponse

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