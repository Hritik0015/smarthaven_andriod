package com.prakash.CamMandu.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prakash.CamMandu.entity.Product
import com.prakash.CamMandu.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(val repository: ProductRepository):ViewModel(){
    private val _text = MutableLiveData<MutableList<Product>>()

    val text: LiveData<MutableList<Product>>
    get()=_text

    fun getAllProducts(){
        viewModelScope.launch{
            val text = repository.displayAllProducts()
            _text.value = text!!
        }
    }
}