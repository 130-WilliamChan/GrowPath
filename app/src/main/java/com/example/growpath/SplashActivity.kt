package com.example.growpath

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 1500L // 1.5 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Using try-catch to identify any initialization errors
        try {
            // We don't need setContentView for a splash screen that uses a theme
            Handler(Looper.getMainLooper()).postDelayed({
                try {
                    val mainIntent = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(mainIntent)
                    finish() // Close this activity
                } catch (e: Exception) {
                    // Log the error for debugging purposes
                    Log.e("SplashActivity", "Error starting LoginActivity", e)

                    // We could show an error dialog here in a production app
                    finish() // Make sure to close the app if transition fails
                }
            }, SPLASH_DISPLAY_LENGTH)
        } catch (e: Exception) {
            Log.e("SplashActivity", "Error in onCreate", e)
            finish() // Close the app if we can't even initialize properly
        }
    }
}