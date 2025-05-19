package com.itsyasirali.ricediseasedetection.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.itsyasirali.ricediseasedetection.R
import com.itsyasirali.ricediseasedetection.util.SharedPrefManager

class ActivitySplash : AppCompatActivity() {

    private lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPrefManager = SharedPrefManager(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (sharedPrefManager.isLoggedIn()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, ActivityLogin::class.java))
            }
            finish()
        }, 1500)
    }
}
