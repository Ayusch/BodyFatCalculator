package com.ayusch.bodyfatcalculator.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.ayusch.bodyfatcalculator.BodyFatApplication


/**
 * Created by Ayus'c'h Jain
 * on 2019-09-29
 *
 */
class SharedPreferenceManager private constructor(context: Context) {
    val sharedPreference: SharedPreferences = context.getSharedPreferences(
        "body_fat_prefs",
        Context.MODE_PRIVATE
    )

    companion object {

        private var sInstance: SharedPreferenceManager? = null

        val KEY_ALERT_COUNT = "alert_count"

        @Synchronized
        fun getInstance(context: Context): SharedPreferenceManager {
            if (sInstance == null) {
                synchronized(SharedPreferenceManager::class.java) {
                    if (sInstance == null) {
                        sInstance = SharedPreferenceManager(context)
                    }
                }
            }
            return sInstance!!
        }

        fun getBooleanValueForKey(key: String, defaultValue: Boolean): Boolean {
            return SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .getBoolean(key, defaultValue)
        }

        fun setBooleanValueForKey(key: String, value: Boolean) {
            SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!.applicationContext!!)
                .sharedPreference
                .edit()
                .putBoolean(key, value)
                .apply()
        }

        fun getIntegerValueForKey(key: String, defaultValue: Int): Int {
            return if (TextUtils.isEmpty(key)) defaultValue else SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .getInt(key, defaultValue)
        }

        fun setIntegerValueForKey(key: String, value: Int) {
            SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .edit()
                .putInt(key, value)
                .apply()
        }

        @SuppressLint("ApplySharedPref")
        fun setIntegerValueForKeySync(key: String, value: Int) {
            SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .edit()
                .putInt(key, value)
                .commit()
        }

        fun getLongValueForKey(key: String, defaultValue: Long): Long {
            return SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .getLong(key, defaultValue)
        }

        fun setLongValueForKey(key: String, value: Long) {
            SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .edit()
                .putLong(key, value)
                .apply()
        }

        fun getFloatValueForKey(key: String, defaultValue: Float): Float {
            return SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .getFloat(key, defaultValue)
        }

        fun setFloatValueForKey(key: String, value: Float) {
            SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .edit()
                .putFloat(key, value)
                .apply()
        }

        fun getStringValueForKey(key: String?, defaultValue: String): String? {
            return if (key == null) null else SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .getString(key, defaultValue)
        }

        fun setStringValueForKey(key: String, value: String) {
            SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .edit()
                .putString(key, value)
                .apply()
        }

        fun getStringSetForKey(key: String): Set<String>? {
            return SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .getStringSet(key, null)
        }

        @SuppressLint("ApplySharedPref")
        fun setStringValueForKeySync(key: String, value: String) {
            SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .edit()
                .putString(key, value)
                .commit()
        }

        operator fun contains(key: String): Boolean {
            return SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .contains(key)
        }

        fun removeKey(key: String) {
            SharedPreferenceManager
                .getInstance(BodyFatApplication.getInstance()?.applicationContext!!)
                .sharedPreference
                .edit()
                .remove(key)
                .apply()
        }

        fun clearOnLogout() {
            if (sInstance != null) {
                sInstance!!.sharedPreference.edit().clear().apply()
            }
            sInstance = null
        }
    }
}