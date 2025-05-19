package com.itsyasirali.ricediseasedetection.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.itsyasirali.ricediseasedetection.databinding.ActivitySignupBinding
import com.itsyasirali.ricediseasedetection.models.UserModel
import com.itsyasirali.ricediseasedetection.viewmodel.UserViewModel

class ActivitySignup : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val user = UserModel(name = name, email = email, password = password)
                userViewModel.createUser(user)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        userViewModel.createStatus.observe(this) { status ->
            status?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                finish() // Go back to login screen after signup
            }
        }

        userViewModel.error.observe(this) {
            it?.let { err -> Toast.makeText(this, err, Toast.LENGTH_SHORT).show() }
        }
    }
}
