package com.yasiraliraj.ricediseasedetection.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.yasiraliraj.ricediseasedetection.R

class ActivitySettings : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val btnEnglish = findViewById<Button>(R.id.btnEnglish)
        val btnUrdu = findViewById<Button>(R.id.btnUrdu)

        val prefs = getSharedPreferences("app_settings", MODE_PRIVATE)

        btnEnglish.setOnClickListener {
            prefs.edit().putString("lang", "en").apply()
            restartApp()
        }

        btnUrdu.setOnClickListener {
            prefs.edit().putString("lang", "ur").apply()
            restartApp()
        }
    }

    private fun restartApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
