package com.iamsdt.themelibrary

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatDelegate

/**
 * Created by Shudipto Trafder on 1/18/2018.
 * at 9:05 PM
 */
class ThemeUtils {

    companion object {

        /**
         * This methods for select theme from
         * shared preference that saved in color activity
         *
         * @param activity to select theme
         */
        fun initialize(activity: Activity) {
            val sp: SharedPreferences = activity.getSharedPreferences(ConstantUtil.colorSp,
                    Context.MODE_PRIVATE)

            val id = sp.getInt(ConstantUtil.themeKey, R.style.LibraryAppTheme_NoActionBar)

            activity.setTheme(id)
            setNightMode(activity)
        }

        /**
         * set nightMode to activity
         * Check user settings first
         *
         * @param context for get shared preference
         */
        private fun setNightMode(context: Context) {

            val sharedPreferences = context.getSharedPreferences(ConstantUtil.NIGHT_MODE_SP_KEY,
                    Context.MODE_PRIVATE)
            val isEnabled = sharedPreferences.getBoolean(ConstantUtil.NIGHT_MODE_VALUE_KEY,
                    false)

            if (isEnabled) {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}