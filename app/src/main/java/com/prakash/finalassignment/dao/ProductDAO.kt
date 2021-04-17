package com.prakash.finalassignment.dao

import androidx.room.*
import com.prakash.finalassignment.entity.Product
@Dao
interface ProductDAO {
    @Insert
    suspend fun insertProduct(product : Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBulkProduct(product: List<Product>)

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts() : MutableList<Product>

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun DeleteProduct(product: Product)

    @Query("DELETE FROM Product")
    suspend fun DeleteAllProducts()
}