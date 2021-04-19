package com.Hritik.SmartHeaven.repository

import android.util.Log
import com.Hritik.SmartHeaven.api.MyApiRequest
import com.Hritik.SmartHeaven.api.ProductAPI
import com.Hritik.SmartHeaven.api.ServiceBuilder
import com.Hritik.SmartHeaven.dao.ProductDAO
import com.Hritik.SmartHeaven.entity.Product


class ProductRepository (private val productDao: ProductDAO): MyApiRequest() {
    private val productAPI = ServiceBuilder.buildService(ProductAPI::class.java)
//    suspend fun getAllProducts(): ProductResponse {
//        return apiRequest {
//            productAPI.getAllProducts()
//        }
//    }

    suspend fun displayAllProducts() : MutableList<Product>?{
        try {
            val response = apiRequest{productAPI.getAllProducts()}
            saveInRoom(response.data!!)
            return productDao.getAllProducts()
        }
        catch(ex:Exception){
            Log.d("repo",ex.toString())
        }
        return productDao.getAllProducts()
    }

    private suspend fun saveInRoom(products: MutableList<Product>){
        for (product in products){
            productDao.insertProduct(product)
        }
    }
}