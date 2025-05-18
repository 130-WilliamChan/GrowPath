package com.example.growpath

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.growpath.databinding.ActivityHomeBinding
import com.example.growpath.models.Subject
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityHomeBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setupBottomNavigation()
            setupClickListeners()
        } catch (e: Exception) {
            Log.e("HomeActivity", "Error initializing activity", e)
            Toast.makeText(this, "Error initializing home screen", Toast.LENGTH_LONG).show()
            // Fall back to a simpler layout if binding fails
            setContentView(R.layout.activity_home)
        }
    }

    private fun setupBottomNavigation() {
        try {
            binding.bottomNavigation.setOnItemSelectedListener { item ->
                try {
                    when (item.itemId) {
                        R.id.navigation_clock -> {
                            // Handle clock navigation
                            return@setOnItemSelectedListener true
                        }
                        R.id.navigation_home -> {
                            // We're already on home, no need to navigate
                            return@setOnItemSelectedListener true
                        }
                        R.id.navigation_settings -> {
                            // Handle settings navigation
                            return@setOnItemSelectedListener true
                        }
                    }
                    false
                } catch (e: Exception) {
                    Log.e("HomeActivity", "Error in navigation selection", e)
                    false
                }
            }

            // Set default selected item
            binding.bottomNavigation.selectedItemId = R.id.navigation_home
        } catch (e: Exception) {
            Log.e("HomeActivity", "Error setting up bottom navigation", e)
        }
    }

    private fun setupClickListeners() {
        try {
            // Sample click listener for the first subject card
            // In a real app, you would have a RecyclerView with an adapter
            binding.ivSearch.setOnClickListener {
                // Handle search action
            }

            binding.tvViewAllTop.setOnClickListener {
                // Navigate to view all top subjects
            }

            binding.tvViewAllYour.setOnClickListener {
                // Navigate to view all your subjects
            }

            // Simulate clicking on a subject to open DetailActivity
            binding.cvSearch.setOnClickListener {
                try {
                    val intent = Intent(this, DetailActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    Log.e("HomeActivity", "Error opening DetailActivity", e)
                    Toast.makeText(this, "Couldn't open details screen", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Log.e("HomeActivity", "Error setting up click listeners", e)
        }
    }
}