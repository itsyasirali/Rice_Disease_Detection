package com.itsyasirali.ricediseasedetection.ui.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import com.itsyasirali.ricediseasedetection.ActivityLanguage
import com.itsyasirali.ricediseasedetection.databinding.ActivitySettingsBinding
import com.itsyasirali.ricediseasedetection.util.SharedPrefManager

class ActivitySettings : BaseActivity() {

    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            cvLanguage.setOnClickListener {
                startActivity(Intent(this@ActivitySettings, ActivityLanguage::class.java))
            }
            cvLogout.setOnClickListener {
                showLogoutConfirmationDialog()
            }
        }

    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { dialog, _ ->
                SharedPrefManager(this).logout()
                val intent = Intent(this, ActivityLogin::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

}
