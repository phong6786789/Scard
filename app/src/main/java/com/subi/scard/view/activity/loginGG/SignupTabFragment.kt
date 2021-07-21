package com.subi.scard.view.activity.loginGG

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.subi.scard.R
import com.subi.scard.databinding.LoginTabFragmentBinding
import com.subi.scard.databinding.SingupTabFragmentBinding

class SignupTabFragment : Fragment() {
    lateinit var binding: SingupTabFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SingupTabFragmentBinding.inflate(inflater, container, false)


        return binding.root
    }
}