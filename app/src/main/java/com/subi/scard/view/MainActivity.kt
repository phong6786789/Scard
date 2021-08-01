package com.subi.scard.view

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.subi.scard.R
import com.subi.scard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupBottomNav()
    }

    //Thêm item_mxh bottom
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


        //onclick item_mxh fragment
        binding.bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                0 -> findNavController(R.id.fragment).navigate(R.id.scanQRFragment)
                1 -> findNavController(R.id.fragment).navigate(R.id.homeFragment)
                2 -> findNavController(R.id.fragment).navigate(R.id.settingsFragment)
                else -> findNavController(R.id.fragment).navigate(R.id.homeFragment)
            }
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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

    companion object{
        var user = ""
        var avatar = ""
        var idUser = ""

    }
}