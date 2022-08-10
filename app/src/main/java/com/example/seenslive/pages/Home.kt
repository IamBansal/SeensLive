package com.example.seenslive.pages

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.seenslive.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class Home : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawerLayout)

//        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
//        drawerLayout.addDrawerListener(actionBarToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        actionBarToggle.syncState()

        navView = findViewById(R.id.navView)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profileDrawer -> {
                    setCurrentFragment(ProfileFragment())
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    drawerLayout.close()
                }
                R.id.groups -> {
                    Toast.makeText(this, "Friends", Toast.LENGTH_SHORT).show()
                }
                R.id.settings -> {
                    setCurrentFragment(SettingsFragment())
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    drawerLayout.close()
                }
                R.id.gifts -> {
                    Toast.makeText(this, "Gifts to you", Toast.LENGTH_SHORT).show()
                }
                R.id.messageDrawer -> {
                    setCurrentFragment(MessageFragment())
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            true
        }


        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        setCurrentFragment(HomeFragment())
        bottomNavigationView.selectedItemId = R.id.home
        bottomNavigationView.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.home -> {
                    setCurrentFragment(HomeFragment())
                }
                R.id.options -> {
                    if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        this.drawerLayout.closeDrawer(GravityCompat.START)
                    } else {
                        drawerLayout.openDrawer(navView)
                    }
                }
                R.id.message -> {
                    setCurrentFragment(MessageFragment())
                }
                R.id.notification -> {
                    setCurrentFragment(NotificationFragment())
                }
                R.id.search -> {
                    setCurrentFragment(SearchFragment())
                }

            }
            true
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
        } else {
            super.onBackPressed()
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().replace(R.id.flFragment, fragment).commit()
}