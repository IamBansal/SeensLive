package com.example.seenslive.pages.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.seenslive.R
import com.example.seenslive.pages.model.User
import com.example.seenslive.pages.screens.EditProfile
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var tabLayout: TabLayout
    private lateinit var editProfile : FloatingActionButton
    private lateinit var name : TextView
    private lateinit var gender : TextView
    private lateinit var about : TextView
    private lateinit var relationStatus : TextView
    private lateinit var dob : TextView
    private lateinit var country : TextView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_profile, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        tabLayout = layout.findViewById(R.id.tabs)
        editProfile = layout.findViewById(R.id.fabEditProfile)
        name = layout.findViewById(R.id.tvNameProfile)
        gender = layout.findViewById(R.id.tvGender)
        about = layout.findViewById(R.id.tvAbout)
        relationStatus = layout.findViewById(R.id.tvRelation)
        dob = layout.findViewById(R.id.tvDOB)
        country = layout.findViewById(R.id.tvCountry)

        editProfile.setOnClickListener {
            startActivity(Intent(context, EditProfile::class.java))
        }

        //To set different width for a tab.
        setTabWidth(0, 0.4f, tabLayout)
        setTabWidth(1, 1.0f, tabLayout)
        setTabWidth(2, 1.0f, tabLayout)
        setTabWidth(3, 1.0f, tabLayout)
        setTabWidth(4, 1.0f, tabLayout)
        setTabWidth(5, 1.0f, tabLayout)
        setTabWidth(6, 1.0f, tabLayout)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> Toast.makeText(context, "This is timeline", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(context, "This is photo", Toast.LENGTH_SHORT).show()
                    2 -> Toast.makeText(context, "This is friends", Toast.LENGTH_SHORT).show()
                    3 -> Toast.makeText(context, "This is groups", Toast.LENGTH_SHORT).show()
                    4 -> Toast.makeText(context, "This is videos", Toast.LENGTH_SHORT).show()
                    5 -> Toast.makeText(context, "This is likes", Toast.LENGTH_SHORT).show()
                    6 -> Toast.makeText(context, "This is events", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        getInfo()

        return layout
    }


    private fun getInfo() {
        FirebaseDatabase.getInstance().getReference("Users")
            .child(firebaseAuth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    name.text = "${user?.FirstName} ${user?.LastName}"
                    about.text = user?.About
                    gender.text = user?.Gender
                    relationStatus.text = user?.Relation
                    dob.text = user?.Birthdate
                    country.text = user?.Country
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }

    //Function to set different width for a tab.
    private fun setTabWidth(tabPosition: Int, weight: Float, tabLayout: TabLayout) {
        val layout: LinearLayout =
            (tabLayout.getChildAt(0) as LinearLayout).getChildAt(tabPosition) as LinearLayout
        val layoutParams: LinearLayout.LayoutParams =
            layout.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = weight
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        layout.layoutParams = layoutParams

        val tabLayoutParams: ViewGroup.LayoutParams? = tabLayout.layoutParams
        tabLayoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT
        tabLayout.layoutParams = tabLayoutParams
    }
}