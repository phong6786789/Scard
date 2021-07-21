package com.subi.scard.view.activity.loginGG

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.subi.scard.databinding.LoginTabFragmentBinding


class LoginTabFragment : Fragment() {
    var v = 0f
    private var _binding: LoginTabFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginTabFragmentBinding.inflate(inflater, container, false)

//hiệu ứng chuyển động
        binding.email.setTranslationX(800f)
        binding.pass.setTranslationX(800f)
        binding.forgetPass.setTranslationX(800f)
        binding.btnLogin.setTranslationX(800f)

        binding.email.setAlpha(v)
        binding.pass.setAlpha(v)
        binding.forgetPass.setAlpha(v)
        binding.btnLogin.setAlpha(v)

        binding.email.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(300).start()
        binding.pass.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(500).start()
        binding.forgetPass.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(500).start()
        binding.btnLogin.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(700).start()

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}