package com.prakash.CamMandu.repository

import com.prakash.CamMandu.api.MyApiRequest
import com.prakash.CamMandu.api.ServiceBuilder
import com.prakash.CamMandu.api.SingleProductAPI
import com.prakash.CamMandu.response.ProductResponse

class SingleProductRepository: MyApiRequest() {
    private val singleProductAPI = ServiceBuilder.buildService(SingleProductAPI::class.java)

    //Display Single Product
    suspend fun getSingleProduct(id:String):ProductResponse{
        return apiRequest {
            singleProductAPI.showSingleProduct(id)
        }
    }
}