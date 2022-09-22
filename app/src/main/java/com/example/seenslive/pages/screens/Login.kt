package com.example.seenslive.pages.screens

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.seenslive.R
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var signUp : TextView
    private lateinit var forgotPass : TextView
    private lateinit var login : Button
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signUp = findViewById(R.id.tvNewUser)
        forgotPass = findViewById(R.id.tvForgot)
        login = findViewById(R.id.btnSignIn)
        email = findViewById(R.id.etEmailLogin)
        password = findViewById(R.id.etPassLogin)
        firebaseAuth = FirebaseAuth.getInstance()

        forgotPass.setOnClickListener {
            startActivity(Intent(this, ForgotPass::class.java))
        }

        signUp.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }

        login.setOnClickListener {
            loginUser()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
    private fun loginUser() {
        val emailText = email.text.toString().trim()
        val passwordText = password.text.toString().trim()

        if (TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passwordText)) {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Login failed!!")
                .setMessage("Fill all credentials first.")
                .setPositiveButton("Okay"){_,_-> }
                .create()
                .show()
        } else {
            val progressBar = ProgressDialog(this)
            progressBar.setMessage("Logging in..")
            progressBar.show()

            firebaseAuth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener { task ->
                progressBar.dismiss()
                if(task.isSuccessful) {
//                    if(firebaseAuth.currentUser!!.isEmailVerified){
                        startActivity(Intent(this, Home::class.java))
//                    } else {
//                        Toast.makeText(this, "Email is not verified yet.", Toast.LENGTH_SHORT).show()
//                    }
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this, Home::class.java))
        }
    }

}