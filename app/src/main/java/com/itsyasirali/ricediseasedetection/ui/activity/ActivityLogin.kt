package com.itsyasirali.ricediseasedetection.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.itsyasirali.ricediseasedetection.databinding.ActivityLoginBinding
import com.itsyasirali.ricediseasedetection.util.SharedPrefManager
import com.itsyasirali.ricediseasedetection.viewmodel.UserViewModel

class ActivityLogin : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefManager = SharedPrefManager(this)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                userViewModel.fetchAllUsers()
            } else {
                Toast.makeText(this, "Email and password required", Toast.LENGTH_SHORT).show()
            }
        }

        userViewModel.allUsers.observe(this) { users ->
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            val user = users.find { it.email == email && it.password == password }
            if (user != null) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                sharedPrefManager.saveUser(user)
                startActivity(Intent(this, MainActivity::class.java)) // Your main app screen
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        userViewModel.error.observe(this) {
            it?.let { err -> Toast.makeText(this, err, Toast.LENGTH_SHORT).show() }
        }

        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, ActivitySignup::class.java))
        }
    }
}
