package com.subi.scard.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.subi.scard.R
import com.subi.scard.databinding.ActivityPlashScreenBinding

class PlashScreen : AppCompatActivity() {
    lateinit var binding: ActivityPlashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plash_screen)
        //Full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityPlashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Next screen
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, 3000)
    }
}