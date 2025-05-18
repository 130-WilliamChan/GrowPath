//Settings_Activity.kt

package com.example.growpath

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.growpath.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUserInfo()
        setupClickListeners()
        setupBottomNavigation()
    }

    private fun setupUserInfo() {
        // In a real app, this would come from user session or preferences
        binding.tvUserName.text = "Astro Girl"
        binding.tvUserEmail.text = "Astrogirl@gmail.com"
        binding.tvMobileNo.text = "9425878736"
        binding.tvLanguage.text = "English (US)"
        binding.tvPassword.text = "••••••••"
    }

    private fun setupClickListeners() {
        binding.layoutMobileNo.setOnClickListener {
            // Handle mobile number update
            Toast.makeText(this, "Update mobile number", Toast.LENGTH_SHORT).show()
        }

        binding.layoutLanguage.setOnClickListener {
            // Handle language selection
            Toast.makeText(this, "Change language", Toast.LENGTH_SHORT).show()
        }

        binding.layoutPassword.setOnClickListener {
            // Handle password update
            Toast.makeText(this, "Change password", Toast.LENGTH_SHORT).show()
        }

        binding.layoutNotifications.setOnClickListener {
            // Handle notifications settings
            Toast.makeText(this, "Notification settings", Toast.LENGTH_SHORT).show()
        }

        // Rating stars click listeners
        val ratingStars = listOf(
            binding.star1, binding.star2, binding.star3, binding.star4, binding.star5
        )

        ratingStars.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                // Update UI to reflect rating (filled stars)
                setRating(index + 1)

                // In a real app, you would save this rating
                Toast.makeText(this, "Rated ${index + 1} stars", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setRating(rating: Int) {
        val stars = listOf(
            binding.star1, binding.star2, binding.star3, binding.star4, binding.star5
        )

        stars.forEachIndexed { index, imageView ->
            if (index < rating) {
                imageView.setImageResource(R.drawable.ic_star_filled)
            } else {
                imageView.setImageResource(R.drawable.ic_star_outline)
            }
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_clock -> {
                    // Navigate to Pomodoro
                    startActivity(PomodoroActivity.newIntent(this))
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_home -> {
                    // Navigate to Home
                    startActivity(Intent(this, HomeActivity::class.java))
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_settings -> {
                    // We're already on settings
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        // Set default selected item
        binding.bottomNavigation.selectedItemId = R.id.navigation_settings
    }

    companion object {
        fun newIntent(context: android.content.Context) = android.content.Intent(context, SettingsActivity::class.java)
        }
}

