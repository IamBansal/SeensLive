package com.example.seenslive.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.seenslive.R

class Login : AppCompatActivity() {

    private lateinit var signUp : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signUp = findViewById(R.id.tvNewUser)

        signUp.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}