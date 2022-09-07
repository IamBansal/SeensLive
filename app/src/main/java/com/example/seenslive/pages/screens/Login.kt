package com.example.seenslive.pages.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.seenslive.R

class Login : AppCompatActivity() {

    private lateinit var signUp : TextView
    private lateinit var forgotPass : TextView
    private lateinit var login : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signUp = findViewById(R.id.tvNewUser)
        forgotPass = findViewById(R.id.tvForgot)
        login = findViewById(R.id.btnSignIn)

        forgotPass.setOnClickListener {
            startActivity(Intent(this, ForgotPass::class.java))
        }

        signUp.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }

        login.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}