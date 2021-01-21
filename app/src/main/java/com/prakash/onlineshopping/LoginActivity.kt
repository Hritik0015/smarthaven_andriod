package com.prakash.onlineshopping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() ,View.OnClickListener{
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var signUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        password = findViewById(R.id.password)
        username = findViewById(R.id.username)
        login = findViewById(R.id.login)
        signUp = findViewById(R.id.signUp)

        signUp.setOnClickListener(this)


    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.signUp -> {
                val intent = Intent(this, SignUp::class.java)
                startActivity(intent)
            }
        }
    }



}