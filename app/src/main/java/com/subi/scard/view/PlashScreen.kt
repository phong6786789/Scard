package com.subi.scard.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.subi.scard.R
import com.subi.scard.databinding.ActivityPlashScreenBinding
import com.subi.scard.utils.Utils
import com.subi.scard.view.intro.IntroActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class PlashScreen : AppCompatActivity() {
    lateinit var binding: ActivityPlashScreenBinding

    @SuppressLint("CheckResult")
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
        io.reactivex.Observable.timer(2000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribe {
                Utils.tempNext(this, IntroActivity::class.java)
            }
    }
}