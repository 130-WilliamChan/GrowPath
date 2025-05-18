package com.example.growpath

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_login)

            val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
            val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)
            val btnLogin = findViewById<Button>(R.id.btnLogin)
            val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
            val tvSignUp = findViewById<TextView>(R.id.tvSignUp)

            btnLogin.setOnClickListener {
                try {
                    // Validate inputs
                    val email = tilEmail.editText?.text?.toString() ?: ""
                    val password = tilPassword.editText?.text?.toString() ?: ""

                    if (email.isEmpty()) {
                        tilEmail.error = "Email is required"
                        return@setOnClickListener
                    } else {
                        tilEmail.error = null
                    }

                    if (password.isEmpty()) {
                        tilPassword.error = "Password is required"
                        return@setOnClickListener
                    } else {
                        tilPassword.error = null
                    }

                    // Proceed with login (replace with actual authentication)
                    try {
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } catch (e: Exception) {
                        Log.e("LoginActivity", "Error starting HomeActivity", e)
                        Toast.makeText(this, "Error navigating to Home screen", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e("LoginActivity", "Error in login process", e)
                    Toast.makeText(this, "An error occurred during login", Toast.LENGTH_SHORT).show()
                }
            }

            tvForgotPassword.setOnClickListener {
                // TODO: Implement forgot password functionality
                Toast.makeText(this, "Forgot password feature coming soon", Toast.LENGTH_SHORT).show()
            }

            tvSignUp.setOnClickListener {
                Toast.makeText(this, "Sign up feature coming soon", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e("LoginActivity", "Error initializing activity", e)
            // Show error message to user
            Toast.makeText(this, "Error initializing app", Toast.LENGTH_LONG).show()
            finish() // Close the activity if there's a critical error
        }
    }
}