package com.subi.scard.view.activity.loginGG

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.subi.scard.R
import com.subi.scard.databinding.LoginTabFragmentBinding
import com.subi.scard.databinding.SingupTabFragmentBinding
import com.subi.scard.utils.SharedPrefs
import com.subi.scard.utils.ShowDialog
import com.subi.scard.utils.Utils
import okhttp3.internal.Util

class SignupTabFragment : Fragment() {
    lateinit var binding: SingupTabFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SingupTabFragmentBinding.inflate(inflater, container, false)
        val loading = context?.let { Utils.loading(it) }

        binding.apply {
            btnSignup.setOnClickListener {
                loading?.show()
                val email = emailSignup.text.toString()
                val pass = passSignup.text.toString()
                val pass2 = passAgain.text.toString()
                if (email.isNotEmpty() && pass.isNotEmpty() && pass2.isNotEmpty()) {

                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        context?.let { it1 ->
                            loading?.dismiss()
                            Utils.showMess(
                                it1,
                                "Địa chỉ email không đúng!"
                            )
                        }
                    } else if (pass == pass2) {
                        val auth = FirebaseAuth.getInstance()
                        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                            if (it.isSuccessful) {
                                context?.let { it1 ->
                                    loading?.dismiss()
                                    SharedPrefs.getInstance().setStringValue(it1, "mail", email)
                                    SharedPrefs.getInstance().setStringValue(it1, "pass", pass)
                                }
                                startActivity(Intent(context, LoginActivity::class.java))
                            }
                        }.addOnFailureListener {
                            loading?.dismiss()
                            context?.let { it1 ->
                                Utils.showMess(
                                    it1,
                                    "Tài khoản đã tồn tại!"
                                )
                            }
                        }
                    } else {
                        loading?.dismiss()
                        context?.let { it1 -> Utils.showMess(it1, "Mật khẩu không khớp nhau!") }
                    }
                } else {
                    loading?.dismiss()
                    context?.let { it1 -> Utils.showMess(it1, "Không được để trống!") }
                }
            }
        }

        return binding.root
    }
}