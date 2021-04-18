package com.prakash.CamMandu.repository

import com.prakash.CamMandu.api.CartAPI
import com.prakash.CamMandu.api.MyApiRequest
import com.prakash.CamMandu.api.ServiceBuilder
import com.prakash.CamMandu.response.CartResponse

class CartRepository (): MyApiRequest() {
    private val cartAPI= ServiceBuilder.buildService(CartAPI::class.java)

    //Insert Product To cart
    suspend fun addTocart(productId:String,token:String): CartResponse {
        return apiRequest {
            cartAPI.insertCart(token!!,productId)
        }
    }

    //Retrieve cart
    suspend fun getCart(token: String): CartResponse {
        return apiRequest {
            cartAPI.retrieveCart(token!!)
        }
    }

//    ///UpdateCart
//    suspend fun updateCart(token:String,id: String,quantity:Int):CartResponse{
//        return apiRequest {
//            cartAPI.updateCart(token!!,id,quantity)
//        }
//    }

    ///DeleteCart
    suspend fun deleteCart(id: String,token:String):CartResponse{
        return apiRequest {
            cartAPI.deleteCart(token!!,id)
        }
    }



}