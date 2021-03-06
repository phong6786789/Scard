package com.subi.scard.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.text.ClipboardManager
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.subi.scard.R
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
        return sharedPrefs.getStringValue(context, Constants.USER.ID_USER, "")
    }

    fun saveFullName(context: Context, key: String) {
        val sharedPrefs = SharedPrefs.getInstance()
        sharedPrefs.setStringValue(context, Constants.USER.FULLNAME, key)
    }

    fun getFullName(context: Context): String? {
        val sharedPrefs = SharedPrefs.getInstance()
        return sharedPrefs.getStringValue(context, Constants.USER.FULLNAME, "")
    }

    //Clear all data
    fun clearAllSharePrefs(context: Context) {
        val sharedPrefs = SharedPrefs.getInstance()
        sharedPrefs.getSharedPref(context).edit().clear().apply()
    }

    fun logOut(context: Context) {
        var dialog: Dialog? = null
        dialog = ShowDialog.Builder(context).title("????NG XU???T")
            .message("B???n c?? ch???c ch???n mu???n ????ng xu???t?")
            .setLeftButton("????NG XU???T", object : LeftInterface {
                override fun onClick() {
                    SharedPrefs.getInstance().deleteAll(context)
                    FirebaseAuth.getInstance().signOut()
                    tempNext(context, LoginActivity::class.java)
                    dialog?.dismiss()
                }

            })
            .setRightButton("KH??NG", object : RightInterface {
                override fun onClick() {
                    dialog?.dismiss()
                }

            })
            .miniDialog()

        dialog?.show()
    }

    fun showMess(context: Context, message: String) {
        var dialog: Dialog? = null
        dialog =
            ShowDialog.Builder(context)
                .title("Th??ng b??o")
                .message(message)
                .setRightButton("????NG", object : RightInterface {
                    override fun onClick() {
                        dialog?.dismiss()
                    }
                })
                .miniDialog()
        dialog?.show()
    }

     fun copyToClipboard(context: Context, text: String, message: String) {
         val clipboard =
             context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
         val clip = ClipData.newPlainText(message, text)
         clipboard.setPrimaryClip(clip)
     }
}