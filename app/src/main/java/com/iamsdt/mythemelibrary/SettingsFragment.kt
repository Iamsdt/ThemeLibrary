package com.iamsdt.mythemelibrary

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.preference.CheckBoxPreference
import android.support.v7.preference.ListPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat

/**
 * Created by Shudipto Trafder on 3/4/2018.
 * at 10:09 PM
 */
class SettingsFragment: PreferenceFragmentCompat(),
        SharedPreferences.OnSharedPreferenceChangeListener{

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Add 'general' preferences, defined in the XML file
        addPreferencesFromResource(R.xml.pref_general)

        val sharedPreferences = preferenceScreen.sharedPreferences

        val count = preferenceScreen.preferenceCount

        (0 until count)
                .asSequence()
                .map { preferenceScreen.getPreference(it) }
                .forEach {
                    when (it) {
                        is CheckBoxPreference -> {
                            val value = sharedPreferences.getBoolean(it.key, false)
                            setPreferenceSummery(it, value)
                        }
                        else -> {
                            val value = sharedPreferences.getString(it.key, "")
                            setPreferenceSummery(it, value)
                        }
                    }
                }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        val preference = findPreference(key)

        if (preference != null) {

            if (preference !is CheckBoxPreference) {
                val value = sharedPreferences?.getString(preference.key, "") ?: ""
                setPreferenceSummery(preference, value)
            }
        }
    }

    private fun setPreferenceSummery(preference: Preference, value: Any) {

        val stringValue = value.toString()

        if (preference is ListPreference) {

            val prefIndex = preference.findIndexOfValue(stringValue)

            if (prefIndex >= 0) {
                preference.summary = preference.entries[prefIndex]
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.summary = stringValue
        }
    }

    override fun onStart() {
        super.onStart()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }
}