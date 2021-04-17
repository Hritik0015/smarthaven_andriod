package com.prakash.finalassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prakash.finalassignment.Adapter.ProductAdapter
import com.prakash.finalassignment.R
import com.prakash.finalassignment.Repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ViewProductActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_product)
        recyclerView = findViewById(R.id.recyclerView)
        loadProduct()
    }
    private fun loadProduct() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val studentRepository = ProductRepository()
                val response = studentRepository.getAllProducts()
                if (response.success == true) {
                    // Insert all the students in room database
                    studentRepository.insertBulkStudent(this@ViewProductActivity, response.data!!)

                    // get data from room database
                    val lstStudents =
                            studentRepository.getAllStudentsFromRoom(this@ViewProductActivity)

                 //   showNotification(lstStudents.size)
                    withContext(Dispatchers.Main) {
                        recyclerView.adapter = ProductAdapter(this@ViewProductActivity, lstStudents!!)
                        recyclerView.layoutManager = LinearLayoutManager(this@ViewProductActivity)
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            this@ViewProductActivity,
                            "Error : ${ex.toString()}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}