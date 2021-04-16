package com.prakash.finalassignment.api

import com.prakash.finalassignment.Response.AddProductResponse
import com.prakash.finalassignment.Response.DeleteProductRsponse
import com.prakash.finalassignment.Response.GetAllProductResponse
import com.prakash.finalassignment.Response.ImageResponse
import com.prakash.finalassignment.entity.Product
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ProductAPI {
    @POST("/insert/product")
    suspend fun addProduct(
        @Header("Authorization") token : String,
        @Body product: Product
    ) : Response<AddProductResponse>
    @GET("/product/all")
    suspend fun getAllProduct(
            @Header("Authorization") token: String
    ) : Response<GetAllProductResponse>

    @DELETE("delete/product/{id}")
    suspend fun deleteStudent(
            @Header("Authorization") token: String,
            @Path("id") id: String
    ): Response<DeleteProductRsponse>

    @Multipart
    @PUT("/insert/product")
    suspend fun uploadImage(
            @Header("Authorization") token: String,
            @Path("id") id: String,
            @Part file: MultipartBody.Part
    ): Response<ImageResponse>

}