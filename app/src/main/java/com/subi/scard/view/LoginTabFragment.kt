package com.example.textkl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.subi.scard.R
import kotlinx.android.synthetic.main.login_tab_fragment.*

class LoginTabFragment : Fragment() {
    var v = 0f
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_tab_fragment, container, false) as ViewGroup


        email.setTranslationX(800f)
        pass.setTranslationX(800f)
        forgetPass.setTranslationX(800f)
        btn_login.setTranslationX(800f)

        email.setAlpha(v)
        pass.setAlpha(v)
        forgetPass.setAlpha(v)
        btn_login.setAlpha(v)

        email.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(300).start()
        pass.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(500).start()
        forgetPass.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(500).start()
        btn_login.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(700).start()

    }
}