package com.example.seenslive.pages.screens

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.seenslive.R
import com.example.seenslive.pages.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EditProfile : AppCompatActivity() {
    
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var firstName : EditText
    private lateinit var lastName : EditText
    private lateinit var gender : EditText
    private lateinit var status : EditText
    private lateinit var country : EditText
    private lateinit var website : EditText
    private lateinit var about : EditText
    private lateinit var dob : EditText
    private lateinit var saveChanges : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        
        firebaseAuth = FirebaseAuth.getInstance()
        firstName = findViewById(R.id.etFirstName)
        lastName = findViewById(R.id.etLastName)
        gender = findViewById(R.id.etGender)
        status = findViewById(R.id.etRelation)
        country = findViewById(R.id.etCountry)
        website = findViewById(R.id.etWebsite)
        about = findViewById(R.id.etAbout)
        dob = findViewById(R.id.etDOB)
        saveChanges = findViewById(R.id.btnSaveChangesEdit)

        saveChanges.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Update requested!!")
                .setMessage("You sure you want to update profile?")
                .setPositiveButton("Okay") { _, _ ->
                   updateInfo()
                }
                .setNegativeButton("No") { _, _ -> }
                .create()
                .show()
        }

    }
    
    private fun updateInfo() {

        val map = HashMap<String, Any>()

        FirebaseDatabase.getInstance().getReference("Users")
            .child(firebaseAuth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    map["Email"] = user?.Email.toString()
                    map["Password"] = user?.Password.toString()
                    map["Username"] = user?.Username.toString()
                    map["id"] = firebaseAuth.currentUser!!.uid
                }

                override fun onCancelled(error: DatabaseError) {}
            })

        map["FirstName"] = firstName.text.toString().trim()
        map["LastName"] = lastName.text.toString().trim()
        map["Gender"] = gender.text.toString().trim()
        map["About"] = about.text.toString().trim()
        map["Relation"] = status.text.toString().trim()
        map["Website"] = website.text.toString().trim()
        map["Birthdate"] = dob.text.toString().trim()
        map["Country"] = country.text.toString().trim()

        val progressBar = ProgressDialog(this)
        progressBar.setMessage("Updating your profile..")
        progressBar.show()

        FirebaseDatabase.getInstance().getReference("Users")
            .child(firebaseAuth.currentUser!!.uid).updateChildren(map).addOnCompleteListener { task ->
                progressBar.dismiss()
                if (task.isSuccessful){
                    onBackPressed()
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
    
}