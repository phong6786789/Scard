package com.subi.scard.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.subi.scard.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //Block back if this in curren fragment
    override fun onBackPressed() {
        val currentFragment = findNavController(R.id.fragment).currentDestination?.id
        if (currentFragment == R.id.homeFragment) {
            return
        } else {
            super.onBackPressed()
        }
    }

}