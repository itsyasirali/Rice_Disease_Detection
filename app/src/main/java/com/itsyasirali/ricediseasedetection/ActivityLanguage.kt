package com.itsyasirali.ricediseasedetection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.itsyasirali.ricediseasedetection.ui.activity.MainActivity

class ActivityLanguage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
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