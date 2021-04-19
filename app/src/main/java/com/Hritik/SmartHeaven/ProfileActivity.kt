package com.Hritik.SmartHeaven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    private lateinit var tvUsername: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvContactno: TextView

    var name: String = ""
    var email: String = ""
    var contactno: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        tvUsername = findViewById(R.id.tvusername)
        tvEmail = findViewById(R.id.tvemail)
        tvContactno = findViewById(R.id.tvcontactno)

        getProfile()

        tvUsername.setText("Username: " + MainActivity.username);
        tvEmail.setText("Email: " + MainActivity.useremail);
        tvContactno.setText("Contact no: " + MainActivity.contact);
    }

    private fun getProfile() {
        val sharedPref = getSharedPreferences("UserDetails", MODE_PRIVATE)
        name = sharedPref.getString("name", "")!!
        email = sharedPref.getString("email", "")!!
        contactno = sharedPref.getString("contactno", "")!!
    }
}