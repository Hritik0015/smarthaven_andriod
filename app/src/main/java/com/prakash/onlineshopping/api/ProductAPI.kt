package com.prakash.onlineshopping.api

import com.prakash.onlineshopping.entity.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ProductAPI {
    @POST("/insert/product")
    suspend fun addStudent(
            @Header("Authorization") token : String,
            @Body product: Product
    ) : Response<AddProductResponse>
}