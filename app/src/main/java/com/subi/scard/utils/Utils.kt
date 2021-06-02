package com.subi.scard.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.subi.scard.databinding.ProgressBarBinding

object Utils {
    fun <T> tempNext(ctx: Context, ofClass: Class<T>) {
        var intent = Intent(ctx, ofClass)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(ctx, intent, null)
    }

    fun log(tag: String, message: String) {
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
}