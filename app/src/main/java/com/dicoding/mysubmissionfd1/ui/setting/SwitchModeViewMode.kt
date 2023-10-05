package com.dicoding.mysubmissionfd1.ui.setting

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SwitchModeViewMode(private val pref: SettingPreferences): ViewModel() {
    fun getThemeSettings(): LiveData<Boolean>{
        return pref.getThemeSetting().asLiveData()
    }
    fun saveThemeSetting(isDarkModeActive: Boolean){
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
        Log.d("isDarkModeActive", isDarkModeActive.toString())
    }
}