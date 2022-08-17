package com.example.seenslive.pages.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.seenslive.R
import com.example.seenslive.pages.screens.EditProfile
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_profile, container, false)

        tabLayout = layout.findViewById(R.id.tabs)
        editProfile = layout.findViewById(R.id.fabEditProfile)

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
                    4 -> Toast.makeText(context, "This is groups", Toast.LENGTH_SHORT).show()
                    5 -> Toast.makeText(context, "This is groups", Toast.LENGTH_SHORT).show()
                    6 -> Toast.makeText(context, "This is groups", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        return layout
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