package com.subi.scard.view

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MotionEventCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.subi.scard.R
import com.subi.scard.databinding.ActivityIntroBinding
import com.subi.scard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
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