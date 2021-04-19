package com.Hritik.SmartHeaven.repository

import com.Hritik.SmartHeaven.api.MyApiRequest
import com.Hritik.SmartHeaven.api.ServiceBuilder
import com.Hritik.SmartHeaven.api.SingleProductAPI
import com.Hritik.SmartHeaven.response.ProductResponse

class SingleProductRepository: MyApiRequest() {
    private val singleProductAPI = ServiceBuilder.buildService(SingleProductAPI::class.java)

    //Display Single Product
    suspend fun getSingleProduct(id:String):ProductResponse{
        return apiRequest {
            singleProductAPI.showSingleProduct(id)
        }
    }
}