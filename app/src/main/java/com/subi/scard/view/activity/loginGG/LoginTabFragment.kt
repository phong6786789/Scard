package com.subi.scard.view.activity.loginGG

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.subi.scard.R
import com.subi.scard.databinding.DialogConfirmBinding
import com.subi.scard.databinding.LayoutResetPassBinding
import com.subi.scard.databinding.LoginTabFragmentBinding
import com.subi.scard.utils.RightInterface
import com.subi.scard.utils.SharedPrefs
import com.subi.scard.utils.ShowDialog
import com.subi.scard.utils.Utils
import com.subi.scard.view.MainActivity
import kotlin.text.isNotEmpty as isNotEmpty1

class LoginTabFragment : Fragment() {
    lateinit var binding: LoginTabFragmentBinding
    var v = 0f
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginTabFragmentBinding.inflate(inflater, container, false)
        val loading = context?.let { Utils.loading(it) }


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
            forgetPass.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(500)
                .start()
            btnLogin.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(700)
                .start()


            context?.let {
                val e = SharedPrefs.getInstance().getStringValue(it, "mail", "")
                val r = SharedPrefs.getInstance().getStringValue(it, "pass", "")

                if (e?.isNotEmpty1() == true && r?.isNotEmpty1() == true) {
                    email.setText(e)
                    pass.setText(r)

                    Utils.showMess(
                        requireContext(),
                        "Đăng ký tài khoản thành công!"
                    )
                }

                btnLogin.setOnClickListener {
                    loading?.show()
                    val emailx = email.text.toString()
                    val passx = pass.text.toString()
                    if (emailx.isNotEmpty1() && passx.isNotEmpty1()) {

                        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailx).matches()) {
                            context?.let { it1 ->
                                Utils.showMess(
                                    it1,
                                    "Địa chỉ email không đúng!"
                                )
                            }
                        } else {
                            val auth = FirebaseAuth.getInstance()
                            auth.signInWithEmailAndPassword(emailx, passx)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        loading?.dismiss()
                                        auth.currentUser?.uid?.let { it1 ->
                                            Utils.saveIdUser(
                                                requireContext(),
                                                it1
                                            )
                                        }
                                        Utils.tempNext(requireContext(), MainActivity::class.java)
                                    }
                                }.addOnFailureListener {
                                    loading?.dismiss()
                                    context?.let { it1 ->
                                        Utils.showMess(
                                            it1,
                                            "Tài khoản hoặc mật khẩu không chính xác!"
                                        )
                                    }
                                }
                        }
                    }
                }
                forgetPass.setOnClickListener {
                    val dialog = context?.let { Dialog(it) }
                    val binding = LayoutResetPassBinding.inflate(LayoutInflater.from(context))
                    dialog?.setContentView(binding.root)
                    dialog?.setCancelable(false)
                    val window = dialog?.window
                    window?.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    if (dialog?.window != null) {
                        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    }

                    dialog?.window?.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog)

                    binding.apply {
                        btnReset.setOnClickListener {
                            loading?.show()
                            if (edtEmail.text.toString().isNotEmpty1()) {
                                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtEmail.text.toString())
                                        .matches()
                                ) {
                                    context?.let { it1 ->
                                        loading?.dismiss()
                                        Utils.showMess(
                                            it1,
                                            "Địa chỉ email không đúng!"
                                        )
                                    }
                                } else {
                                    FirebaseAuth.getInstance()
                                        .sendPasswordResetEmail(edtEmail.text.toString())
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                loading?.dismiss()
                                                dialog?.dismiss()
                                                Utils.showMess(
                                                    requireContext(),
                                                    "Reset mật khẩu thành công!\nVui lòng kiểm tra mail của bạn."
                                                )
                                            }
                                        }
                                        .addOnFailureListener {
                                            loading?.dismiss()
                                            Utils.showMess(
                                                requireContext(),
                                                "Tài khoản không tồn tại!"
                                            )
                                        }
                                }
                            } else {
                                loading?.dismiss()
                                Utils.showMess(
                                    requireContext(),
                                    "Không được để trống!"
                                )
                            }
                        }

                        btnClose.setOnClickListener {
                            dialog?.dismiss()
                        }
                    }

                    dialog?.show()
                }
            }
        }


        return binding.root
    }
}