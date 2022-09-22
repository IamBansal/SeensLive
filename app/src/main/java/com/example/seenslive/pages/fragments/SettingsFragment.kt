package com.example.seenslive.pages.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.seenslive.R
import com.example.seenslive.pages.screens.Login
import com.google.firebase.auth.FirebaseAuth

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SettingsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var signOutBtn : Button
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_settings, container, false)

        signOutBtn = layout.findViewById(R.id.btnSignOut)
        firebaseAuth = FirebaseAuth.getInstance()

        signOutBtn.setOnClickListener {
            signOut()
        }

        return layout
    }

    private fun signOut() {

        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle("Logout requested!!")
            .setMessage("You sure you want to logout?")
            .setPositiveButton("Okay") { _, _ ->
                firebaseAuth.signOut()
                startActivity(Intent(context, Login::class.java))
            }
            .setNegativeButton("No") { _, _ -> }
            .create()
            .show()

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}