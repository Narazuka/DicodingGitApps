package com.dicoding.mysubmissionfd1.ui.detail_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mysubmissionfd1.R
import com.dicoding.mysubmissionfd1.ui.setting.SettingPreferences
import com.dicoding.mysubmissionfd1.ui.setting.SwitchModeViewMode
import com.dicoding.mysubmissionfd1.ui.setting.ViewModelFactory
import com.dicoding.mysubmissionfd1.ui.setting.dataStore
import com.google.android.material.switchmaterial.SwitchMaterial

class SwitchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch)


        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)
        val pref = SettingPreferences.getInstance(application.dataStore)
        val switchViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SwitchModeViewMode::class.java
        )
        switchViewModel.getThemeSettings().observe(this){
                isDarkModeActive: Boolean ->
            if (isDarkModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }
        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            switchViewModel.saveThemeSetting(isChecked)
        }
    }
}