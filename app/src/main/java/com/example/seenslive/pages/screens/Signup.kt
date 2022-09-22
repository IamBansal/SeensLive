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
import com.google.firebase.database.FirebaseDatabase

class Signup : AppCompatActivity() {

    private lateinit var login : TextView
    private lateinit var emailET : EditText
    private lateinit var passET : EditText
    private lateinit var firstNameET : EditText
    private lateinit var lastNameET : EditText
    private lateinit var userNameET : EditText
    private lateinit var signup : Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firebaseAuth = FirebaseAuth.getInstance()
        login = findViewById(R.id.tvAlreadyUser)
        signup = findViewById(R.id.btnSignUp)
        emailET = findViewById(R.id.etEmailSignup)
        passET = findViewById(R.id.etPassSignup)
        firstNameET = findViewById(R.id.etFirstName)
        lastNameET = findViewById(R.id.etLastName)
        userNameET = findViewById(R.id.etUsername)

        login.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        signup.setOnClickListener {
            signUpUser()
        }

    }


    private fun signUpUser() {

        val firstName = firstNameET.text.toString().trim()
        val lastName = lastNameET.text.toString().trim()
        val username = userNameET.text.toString().trim()
        val email = emailET.text.toString().trim()
        val password = passET.text.toString().trim()

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(username)
            || TextUtils.isEmpty(password)
        ) {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Signup failed!!")
                .setMessage("Fill all credentials first.")
                .setPositiveButton("Okay") { _, _ -> }
                .create()
                .show()
        } else {

//            if (setPass == confirmPass) {

                val progressBar = ProgressDialog(this)
                progressBar.setMessage("Signing you up..")
                progressBar.show()

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            //Storing user's info
                            val map = HashMap<String, Any>()
                            map["Email"] = email
                            map["FirstName"] = firstName
                            map["LastName"] = lastName
                            map["id"] = firebaseAuth.currentUser!!.uid
                            map["Username"] = username
                            map["Password"] = password
                            map["Gender"] = "ftrh"
                            map["Relation"] = "sugbxuwfx"
                            map["Country"] = "sybxowgf"
                            map["Website"] = "dfergc"
                            map["About"] = "crcgeg"
                            map["Birthdate"] = "cehh"

                            //Updating user's info to realtime database
                            FirebaseDatabase.getInstance().reference.child("Users")
                                .child(firebaseAuth.currentUser!!.uid).updateChildren(map)
                                .addOnCompleteListener { task1 ->
                                    progressBar.dismiss()
                                    if (task1.isSuccessful) {
                                        startActivity(Intent(this, Login::class.java))
                                    } else {
                                        Toast.makeText(
                                            this,
                                            task1.exception?.message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        } else {
                            Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }

//            } else {
//                val alert = AlertDialog.Builder(this)
//                alert.setTitle("Signup failed!!")
//                    .setMessage("Password didn't matched.")
//                    .setPositiveButton("Okay") { _, _ -> }
//                    .create()
//                    .show()
//            }

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Login::class.java))
    }

}