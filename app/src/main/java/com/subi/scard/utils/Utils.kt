package com.subi.scard.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.subi.scard.R
import com.subi.scard.databinding.DialogConfirmBinding
import com.subi.scard.databinding.ProgressBarBinding
import com.subi.scard.view.activity.loginGG.LoginActivity

object Utils {
    fun <T> tempNext(ctx: Context, ofClass: Class<T>) {
        var intent = Intent(ctx, ofClass)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(ctx, intent, null)
    }

    fun <T> tempNextNoClear(ctx: Context, ofClass: Class<T>) {
        var intent = Intent(ctx, ofClass)
        ContextCompat.startActivity(ctx, intent, null)
    }

    fun log(tag: String?, message: String) {
        Log.d(tag, message)
    }

    fun showProgressBar(context: Context, message: String): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        val binding = ProgressBarBinding.inflate(LayoutInflater.from(context))
        binding.textMessage.text = message
        builder.setView(binding.root)
        return builder.create()
    }

    fun loading(context: Context): Dialog {
        var dialog = Dialog(context)
        dialog.setContentView(R.layout.loading)
        dialog.setCancelable(false)
        val window = dialog.window
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    fun saveIdUser(context: Context, key: String) {
        val sharedPrefs = SharedPrefs.getInstance()
        sharedPrefs.setStringValue(context, Constants.USER.ID_USER, key)
    }

    fun getIdUser(context: Context): String? {
        val sharedPrefs = SharedPrefs.getInstance()
        val stringValue = sharedPrefs.getStringValue(context, Constants.USER.ID_USER, null)
        return stringValue
    }

    //Clear all data
    fun clearAllSharePrefs(context: Context) {
        val sharedPrefs = SharedPrefs.getInstance()
        sharedPrefs.getSharedPref(context).edit().clear().apply()
    }

    fun logOut(context: Context) {
        var dialog: Dialog? = null
        dialog = ShowDialog.Builder(context).title("ĐĂNG XUẤT")
            .message("Bạn có chắc chắn muốn đăng xuất?")
            .setLeftButton("ĐĂNG XUẤT", object : LeftInterface {
                override fun onClick() {
                    FirebaseAuth.getInstance().signOut()
                    tempNext(context, LoginActivity::class.java)
                }

            })
            .setRightButton("KHÔNG", object : RightInterface {
                override fun onClick() {
                    dialog?.dismiss()
                }

            })
            .miniDialog()

        dialog?.show()
    }
}