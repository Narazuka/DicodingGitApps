package com.dicoding.mysubmissionfd1.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dicoding.mysubmissionfd1.R
import com.dicoding.mysubmissionfd1.ui.MainActivity

class SplashScreenaActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 3000 // Durasi splash screen dalam milidetik (3 detik)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screena)

        Handler().postDelayed({
            // Pindah ke MainActivity setelah splashTimeOut
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Tutup activity splash agar tidak bisa diakses lagi
        }, splashTimeOut)
    }
}