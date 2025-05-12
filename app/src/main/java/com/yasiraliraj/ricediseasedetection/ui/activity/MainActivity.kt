package com.yasiraliraj.ricediseasedetection.ui.activity

import android.content.Intent
import android.os.Bundle
import com.yasiraliraj.ricediseasedetection.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCardClickListeners()
    }

    private fun setCardClickListeners() = with(binding) {
        cardCheckDisease.setOnClickListener { navigateTo(ActivityCheckRice::class.java) }
        cardHistory.setOnClickListener { navigateTo(ActivityHistory::class.java) }
        cardCommonDiseases.setOnClickListener { navigateTo(ActivityCommonDiseases::class.java) }
        cardUserManual.setOnClickListener { navigateTo(ActivityUserManual::class.java) }
        cardSettings.setOnClickListener { navigateTo(ActivitySettings::class.java) }
    }

    private fun navigateTo(destination: Class<*>) {
        startActivity(Intent(this, destination))
    }
}