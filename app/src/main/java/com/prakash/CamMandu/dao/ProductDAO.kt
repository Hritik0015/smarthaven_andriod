package com.prakash.CamMandu.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prakash.CamMandu.entity.Product
@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts():MutableList<Product>
}