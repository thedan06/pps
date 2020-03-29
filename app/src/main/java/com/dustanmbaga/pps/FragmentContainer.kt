package com.dustanmbaga.pps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dustanmbaga.pps.acaricide.AcaricideFragment
import com.dustanmbaga.pps.ui.learn.LearnFragment
import com.dustanmbaga.pps.ui.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentContainer: Fragment() {

    private val onBottomNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        var selectedFragment: Fragment? = null

        when (item.itemId) {
            R.id.navigation_acaricides -> {
                selectedFragment = AcaricideFragment()
            }
            R.id.navigation_learn -> {
                selectedFragment = LearnFragment()
            }
            R.id.navigation_settings -> {
                selectedFragment = SettingsFragment()
            }
        }

        replaceFragment(selectedFragment!!)

        true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val navView: BottomNavigationView = view.findViewById(R.id.bottom_nav_view)

        navView.setOnNavigationItemSelectedListener(onBottomNavItemSelectedListener)

        if (savedInstanceState == null) {
            replaceFragment(AcaricideFragment())
        }

        return view
    }

    private fun replaceFragment(fragment: Fragment) {
        val manager: FragmentManager = (context as AppCompatActivity).supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()

        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}