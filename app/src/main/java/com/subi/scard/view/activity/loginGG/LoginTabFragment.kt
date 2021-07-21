package com.subi.scard.view.activity.loginGG
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.subi.scard.databinding.LoginTabFragmentBinding

class LoginTabFragment: Fragment() {
    lateinit var binding:LoginTabFragmentBinding
    var v = 0f
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginTabFragmentBinding.inflate(inflater, container, false)


        binding.apply {
            email.translationX = 800f
            pass.translationX = 800f
            forgetPass.translationX = 800f
            btnLogin.translationX = 800f

            email.alpha = v
            pass.alpha = v
            forgetPass.alpha = v
            btnLogin.alpha = v

            email.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(300).start()
            pass.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(500).start()
            forgetPass.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(500).start()
            btnLogin.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(700).start()
        }


        return binding.root
    }
}