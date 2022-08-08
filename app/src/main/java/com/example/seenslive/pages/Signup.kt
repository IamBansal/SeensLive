package com.example.seenslive.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.seenslive.R

class Signup : AppCompatActivity() {

    private lateinit var login : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        login = findViewById(R.id.tvAlreadyUser)

        login.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

    }
}