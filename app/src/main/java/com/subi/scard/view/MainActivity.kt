package com.subi.scard.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.subi.scard.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setHasOptionsMenu(true)
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

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        MenuInflater.inflate(R.menu.menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.logout -> {
//                logOut()
//                return true
//            }
//            R.id.qr -> {
//                Toast.makeText(activity, "QR", Toast.LENGTH_SHORT).show()
//                return true
//            }
//            else -> {
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}