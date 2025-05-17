package com.example.growpath

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.growpath.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            // Validate login credentials
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateCredentials(email, password)) {
                // For demonstration purposes, we'll just navigate to HomeActivity
                // In a real app, you'd authenticate with a server
                navigateToHome()
            }
        }

        binding.tvForgotPassword.setOnClickListener {
            // Handle forgot password click
            Toast.makeText(this, "Forgot password functionality", Toast.LENGTH_SHORT).show()
        }

        binding.tvSignUp.setOnClickListener {
            // Handle sign up click
            Toast.makeText(this, "Sign up functionality", Toast.LENGTH_SHORT).show()
        }

        // Social login buttons
        binding.cvFacebook.setOnClickListener {
            Toast.makeText(this, "Facebook login", Toast.LENGTH_SHORT).show()
        }

        binding.cvGoogle.setOnClickListener {
            Toast.makeText(this, "Google login", Toast.LENGTH_SHORT).show()
        }

        binding.cvApple.setOnClickListener {
            Toast.makeText(this, "Apple login", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateCredentials(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.tilEmail.error = "Email is required"
            return false
        } else {
            binding.tilEmail.error = null
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "Password is required"
            return false
        } else {
            binding.tilPassword.error = null
        }

        // Add more validation as needed (email format, password strength, etc.)
        return true
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish() // Close LoginActivity so user cannot go back
    }
}