package com.subi.scard.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager.LayoutParams.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.subi.scard.R
import com.subi.scard.databinding.ActivityPlashScreenBinding
import com.subi.scard.utils.Utils
import com.subi.scard.view.activity.intro.IntroActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

@Suppress("DEPRECATION")
class PlashScreen : AppCompatActivity() {
    lateinit var binding: ActivityPlashScreenBinding

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plash_screen)
        //Full screen
        window.setFlags(
            FLAG_FULLSCREEN,
            FLAG_FULLSCREEN
        )

        //Hide actionbar
        supportActionBar?.hide()
        binding = ActivityPlashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Next screen
        io.reactivex.Observable.timer(1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribe {
                val uid = Utils.getIdUser(this)
                if (uid != null) {
                    Utils.tempNext(this, MainActivity::class.java)
                } else {
                    Utils.tempNextNoClear(this, IntroActivity::class.java)
                }
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
    }
}