package com.prakash.finalassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.prakash.finalassignment.R
import com.prakash.finalassignment.Repository.UserRepository

import com.prakash.finalassignment.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupActivity : AppCompatActivity() {
    private lateinit var etfname:EditText
    private lateinit var etlname:EditText
    private lateinit var etage:EditText
    private lateinit var etusername:EditText
    private lateinit var etgender:EditText
    private lateinit var etpassword:EditText
    private lateinit var etconpassword:EditText
    private lateinit var btnsignup:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etfname = findViewById(R.id.etfname)
        etlname = findViewById(R.id.etlname)
        etgender = findViewById(R.id.etgen)
        etusername = findViewById(R.id.etusername)
        etage = findViewById(R.id.etage)
        etpassword = findViewById(R.id.etpassword)
        etconpassword = findViewById(R.id.etconfpassword)
        btnsignup = findViewById(R.id.btnsignup)

        btnsignup.setOnClickListener {
            Toast.makeText(
                this@SignupActivity, "Register successful", Toast.LENGTH_LONG).show()
            val fname = etfname.text.toString()
            val lname = etlname.text.toString()
            val username = etusername.text.toString()
            val password = etpassword.text.toString()
            val confirmPassword = etconpassword.text.toString()
            val age=etage.text.toString()
            val gender=etgender.text.toString()

            if (password != confirmPassword) {
                etpassword.error = "Password does not match"
                etpassword.requestFocus()
                return@setOnClickListener
            } else {
                val user =
                    User( firstname = fname, lastname = lname, username = username, password = password,age = age,email = gender)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val userRepository = UserRepository()
                        val response = userRepository.registerUser(user)
                        if (response.success == true) {
                            withContext(Main) {
                                Toast.makeText(
                                    this@SignupActivity, "Register successful", Toast.LENGTH_LONG).show()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Main) {
                            Toast.makeText(
                                this@SignupActivity, ex.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}