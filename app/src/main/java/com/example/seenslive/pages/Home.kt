package com.example.seenslive.pages

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.seenslive.R
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
                    Toast.makeText(this, "Friends", Toast.LENGTH_SHORT).show()
                }
                R.id.settings -> {
                    setCurrentFragment(SettingsFragment(), 0)
                }
                R.id.gifts -> {
                    Toast.makeText(this, "Gifts to you", Toast.LENGTH_SHORT).show()
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
                    3 -> setCurrentFragment(MessageFragment(), 3)
                    4 -> setCurrentFragment(NotificationFragment(), 4)
                    6 -> setCurrentFragment(SearchFragment(), 6)
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