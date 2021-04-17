package com.prakash.finalassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.prakash.finalassignment.R

class DashboardActivity : AppCompatActivity() {
    private lateinit var btnAddStudent: Button
    private lateinit var btnViewStudent: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        btnAddStudent = findViewById(R.id.btnAddPrd)
        btnViewStudent = findViewById(R.id.btnViewPrd)

        btnAddStudent.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }

        btnViewStudent.setOnClickListener {
            startActivity(Intent(this, ViewProductActivity::class.java))
        }
    }
}