package com.example.seenslive.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.seenslive.R
import com.google.android.material.tabs.TabLayout

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FriendsFragment : Fragment() {
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
    private lateinit var llDiscover : LinearLayout
    private lateinit var llFriendRequests : LinearLayout
    private lateinit var llSentRequests : LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_friends, container, false)

        tabLayout = layout.findViewById(R.id.tabLayoutFriends)
        llDiscover = layout.findViewById(R.id.llDiscoverFriend)
        llFriendRequests = layout.findViewById(R.id.llFriendRequests)
        llSentRequests = layout.findViewById(R.id.llSentFriendRequests)

        //To set different width for a tab.
        setTabWidth(0, 0.7f, tabLayout)
        setTabWidth(1, 1.0f, tabLayout)
        setTabWidth(2, 1.0f, tabLayout)

        tabLayout.getTabAt(1)?.select()
        llDiscover.visibility = View.GONE
        llFriendRequests.visibility = View.VISIBLE
        llSentRequests.visibility = View.GONE

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        llDiscover.visibility = View.VISIBLE
                        llFriendRequests.visibility = View.GONE
                        llSentRequests.visibility = View.GONE
                    }
                    1 -> {
                        llDiscover.visibility = View.GONE
                        llFriendRequests.visibility = View.VISIBLE
                        llSentRequests.visibility = View.GONE
                    }
                    2-> {
                        llDiscover.visibility = View.GONE
                        llFriendRequests.visibility = View.GONE
                        llSentRequests.visibility = View.VISIBLE
                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        return layout;
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