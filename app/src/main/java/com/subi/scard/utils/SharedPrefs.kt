package com.subi.scard.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs {
    /**
     * Set value.
     *
     * @param ctx   Current context
     * @param key   Key
     * @param value Value
     */
    fun setBooleanValue(ctx: Context, key: String, value: Boolean) {
        val sharedPref = getSharedPref(ctx)
        val editor = sharedPref.edit()
        editor.putBoolean(key, value)
        editor.apply()
        editor.commit()
    }

    /**
     * Set value.
     *
     * @param ctx Current context
     * @param key Key
     */
    fun getBooleanValue(ctx: Context, key: String, defValue: Boolean = false): Boolean {
        val sharedPref = getSharedPref(ctx)
        return sharedPref.getBoolean(key, defValue)
    }

    /**
     * Set value.
     *
     * @param ctx   Current context
     * @param key   Key
     * @param value Value
     */
    fun setStringValue(ctx: Context, key: String, value: String) {
        val sharedPref = getSharedPref(ctx)
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
        editor.commit()
    }


    /**
     * Set value.
     *
     * @param ctx Current context
     * @param key Key
     */
    fun getStringValue(ctx: Context, key: String, defValue: String?): String? {
        val sharedPref = getSharedPref(ctx)
        return sharedPref.getString(key, defValue)
    }

    /**
     * Get value.
     *
     * @param ctx Current context
     * @param key Key
     * @return Value
     */
    fun getIntValue(ctx: Context, key: String, defaultValue: Int = 0): Int {
        val sharedPref = getSharedPref(ctx)
        return sharedPref.getInt(key, defaultValue)
    }

    /**
     * Set value.
     *
     * @param ctx   Current context
     * @param key   Key
     * @param value Value
     */
    fun setIntValue(ctx: Context, key: String, value: Int) {
        val sharedPref = getSharedPref(ctx)
        val editor = sharedPref.edit()
        editor.putInt(key, value)
        editor.apply()
        editor.commit()
    }

    /**
     * Get value.
     *
     * @param ctx Current context
     * @param key Key
     * @return Value
     */
    fun getLongValue(ctx: Context, key: String, defaultValue: Long = 0): Long {
        val sharedPref = getSharedPref(ctx)
        return sharedPref.getLong(key, defaultValue)
    }

    /**
     * Set value.
     *
     * @param ctx   Current context
     * @param key   Key
     * @param value Value
     */
    fun setLongValue(ctx: Context, key: String, value: Long) {
        val sharedPref = getSharedPref(ctx)
        val editor = sharedPref.edit()
        editor.putLong(key, value)
        editor.apply()
        editor.commit()
    }

    /**
     * Get value.
     *
     * @param ctx Current context
     * @param key Key
     * @return Value
     */
    fun getDoubleValue(ctx: Context, key: String, defaultValue: Double = 0.0): Double {
        val sharedPref = getSharedPref(ctx)
        return sharedPref.getFloat(key, defaultValue.toFloat()).toDouble()
    }

    /**
     * Set value.
     *
     * @param ctx   Current context
     * @param key   Key
     * @param value Value
     */
    fun setDoubleValue(ctx: Context, key: String, value: Double) {
        val sharedPref = getSharedPref(ctx)
        val editor = sharedPref.edit()
        editor.putFloat(key, value.toFloat())
        editor.apply()
        editor.commit()
    }

    /**
     * Get My Shared preference.
     *
     * @param ctx Current context
     * @return Shared preference object
     */
    fun getSharedPref(ctx: Context) = ctx.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE)

    companion object {
        // Singleton instance
        private var instance: SharedPrefs? = null
        // Database name
        private const val DB_NAME = "Share_DB"

        /**
         * Get singleton instance.
         *
         * @return Singleton instance
         */
        fun getInstance(): SharedPrefs {
            if (instance == null) {
                instance =
                    SharedPrefs()
            }
            return instance as SharedPrefs
        }
    }
}