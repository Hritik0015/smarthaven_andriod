package com.prakash.finalassignment.Repository

import android.content.Context
import com.prakash.finalassignment.Response.*
import com.prakash.finalassignment.api.MyAPIRequest
import com.prakash.finalassignment.api.ProductAPI
import com.prakash.finalassignment.api.ServiceBuilder
import com.prakash.finalassignment.db.UserDb
import com.prakash.finalassignment.entity.Product
import okhttp3.MultipartBody

class ProductRepository : MyAPIRequest() {

    private val productAPI =
            ServiceBuilder.buildService(ProductAPI::class.java)

    suspend fun insertProduct(product: Product): AddProductResponse {
        return apiRequest {
            productAPI.addProduct(ServiceBuilder.token!!, product)
        }
    }

    suspend fun getAllProducts(): GetAllProductResponse {
        return apiRequest {
            productAPI.getAllProduct(ServiceBuilder.token!!)
        }
    }

    suspend fun insertBulkStudent(context : Context, products : List<Product>){
        // Delete all students
        UserDb.getInstance(context).getProdcutDAO().DeleteAllProducts()
        // Insert all data in database
        UserDb.getInstance(context).getProdcutDAO().insertBulkProduct(products)
    }

    // get data from repository
    suspend fun getAllStudentsFromRoom(context : Context) : MutableList<Product>{
        return UserDb.getInstance(context).getProdcutDAO().getAllProducts()
    }

    suspend fun deleteStudents(id: String): DeleteProductRsponse {
        return apiRequest {
            productAPI.deleteStudent(ServiceBuilder.token!!, id)
        }
    }

    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            productAPI.uploadImage(ServiceBuilder.token!!, id, body)
        }
    }


}