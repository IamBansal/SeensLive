package com.example.seenslive.pages.screens

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.seenslive.R
import com.example.seenslive.pages.fragments.*
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout

class Home : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawerLayout)
        drawerLayout.tag = "Close"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView = findViewById(R.id.navView)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profileDrawer -> {
                    setCurrentFragment(ProfileFragment(), 7)
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    this@Home.drawerLayout.tag = "Close"
                }
                R.id.groups -> {
                    setCurrentFragment(FriendsFragment(), 2)
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    this@Home.drawerLayout.tag = "Close"
                }
                R.id.settings -> {
                    setCurrentFragment(SettingsFragment(), 0)
                }
                R.id.gifts -> {
                    setCurrentFragment(GiftsFragment(), 5)
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    this@Home.drawerLayout.tag = "Close"
                }
                R.id.messageDrawer -> {
                    setCurrentFragment(MessageFragment(), 3)
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    this@Home.drawerLayout.tag = "Close"
                }
            }
            true
        }

        tabLayout = findViewById(R.id.tabLayout)

        setBadge(2, resources.getColor(R.color.primary_dark), resources.getColor(R.color.white), 2)
        setBadge(4, resources.getColor(R.color.hearty), resources.getColor(R.color.white), 5)

        setCurrentFragment(HomeFragment(), 1)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> if (this@Home.drawerLayout.tag.equals("Open")) {
                        this@Home.drawerLayout.closeDrawer(GravityCompat.START)
                        this@Home.drawerLayout.tag = "Close"
                    } else {
                        this@Home.drawerLayout.openDrawer(navView)
                        this@Home.drawerLayout.tag = "Open"
                    }
                    1 -> setCurrentFragment(HomeFragment(), 1)
                    2 -> setCurrentFragment(FriendsFragment(), 2)
                    3 -> setCurrentFragment(MessageFragment(), 3)
                    4 -> setCurrentFragment(NotificationFragment(), 4)
                    5 -> setCurrentFragment(GiftsFragment(), 5)
                    6 -> setCurrentFragment(SearchFragment(), 6)
                    7 -> setCurrentFragment(ProfileFragment(), 7)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> if (this@Home.drawerLayout.tag.equals("Open")) {
                        this@Home.drawerLayout.closeDrawer(GravityCompat.START)
                        this@Home.drawerLayout.tag = "Close"
                    } else {
                        this@Home.drawerLayout.openDrawer(navView)
                        this@Home.drawerLayout.tag = "Open"
                    }
                }
            }
        })
    }

    //To set the badge on tab item
    private fun setBadge(index: Int, backgroundColor: Int, textColor: Int, count: Int) {
        tabLayout.getTabAt(index)?.apply {
            orCreateBadge
            badge?.badgeTextColor = textColor
            badge?.backgroundColor = backgroundColor
            badge?.number = count
            badge?.isVisible = true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        return true
    }

    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
            this.drawerLayout.tag = "Close"
        } else {
            super.onBackPressed()
        }
    }

    private fun setCurrentFragment(fragment: Fragment, position: Int) {
        tabLayout.getTabAt(position)?.select()
        supportFragmentManager.beginTransaction().replace(R.id.flFragment, fragment).commit()
    }
}