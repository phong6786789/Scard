package com.subi.scard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.subi.scard.R
import com.subi.scard.R.id.fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    //Block back
    override fun onBackPressed() {
//        val currentFragment = NavHostFragment.findNavController(R.id.fragment).currentDestination?.id
//        if (currentFragment == R.id.loginFragment||currentFragment == R.id.homeFragment) {
//            return
//        } else {
//            super.onBackPressed()
//        }

    }

}