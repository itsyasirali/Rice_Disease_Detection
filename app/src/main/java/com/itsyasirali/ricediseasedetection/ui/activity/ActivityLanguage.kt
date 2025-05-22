package com.itsyasirali.ricediseasedetection.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.itsyasirali.ricediseasedetection.R
import com.itsyasirali.ricediseasedetection.databinding.ActivityLanguageBinding

class ActivityLanguage : AppCompatActivity() {
    private lateinit var binding: ActivityLanguageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("app_settings", MODE_PRIVATE)

        val savedLang = prefs.getString("lang", "en")
        binding.radioEnglish.isChecked = savedLang == "en"
        binding.radioUrdu.isChecked = savedLang == "ur"

        binding.langRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioEnglish -> {
                    prefs.edit().putString("lang", "en").apply()
                    restartApp()
                }
                R.id.radioUrdu -> {
                    prefs.edit().putString("lang", "ur").apply()
                    restartApp()
                }
            }
        }

        binding.btnBack.setOnClickListener { super.onBackPressed() }
    }

    private fun restartApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}