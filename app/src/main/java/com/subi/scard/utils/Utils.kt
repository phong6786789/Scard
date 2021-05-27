package com.subi.scard.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat

object Utils {
    fun <T> tempNext(ctx: Context, ofClass: Class<T>) {
        var intent = Intent(ctx, ofClass)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(ctx, intent, null)
    }

    fun log(tag: String, message: String) {
       Log.d(tag, message);
    }

}