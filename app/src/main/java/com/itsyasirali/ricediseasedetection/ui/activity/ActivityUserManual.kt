package com.itsyasirali.ricediseasedetection.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.itsyasirali.ricediseasedetection.R
import com.itsyasirali.ricediseasedetection.databinding.ActivityUserManualBinding

class ActivityUserManual : BaseActivity() {
    private lateinit var binding: ActivityUserManualBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserManualBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { super.onBackPressed() }
    }
}