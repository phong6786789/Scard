package com.subi.scard.view

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MotionEventCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.subi.scard.R
import com.subi.scard.databinding.ActivityIntroBinding
import com.subi.scard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        binding.bottomNavigation.add(
            MeowBottomNavigation.Model(
                0,
                R.drawable.ic_baseline_qr_code_scanner_24
            )
        )
        binding.bottomNavigation.add(
            MeowBottomNavigation.Model(
                1,
                R.drawable.ic_baseline_home_24
            )
        )
        binding.bottomNavigation.add(
            MeowBottomNavigation.Model(
                2,
                R.drawable.ic_baseline_settings_24
            )
        )
        //defautl home
        binding.bottomNavigation.show(1)

        //onclick item fragment
        binding.bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                0 -> findNavController(R.id.fragment).navigate(R.id.scanQRFragment)
                1 -> findNavController(R.id.fragment).navigate(R.id.homeFragment)
                2 -> findNavController(R.id.fragment).navigate(R.id.settingsFragment)
            }
        }

    }

    //Block back if this in curren fragment
    override fun onBackPressed() {
        val currentFragment = findNavController(R.id.fragment).currentDestination?.id
        if (currentFragment == R.id.homeFragment) {
            return
        } else {
            binding.bottomNavigation.visibility = View.VISIBLE
            binding.bottomNavigation.show(1)
            findNavController(R.id.fragment).navigate(R.id.homeFragment)
        }
    }
}