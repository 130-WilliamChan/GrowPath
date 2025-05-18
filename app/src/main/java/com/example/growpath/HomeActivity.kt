package com.example.growpath

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.growpath.databinding.ActivityHomeBinding
import com.example.growpath.models.Subject
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
        setupClickListeners()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
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
        }

        // Set default selected item
        binding.bottomNavigation.selectedItemId = R.id.navigation_home
    }

    private fun setupClickListeners() {
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
        // In real implementation, this would be handled by the adapter item click
        val layoutParams = binding.cvSearch.layoutParams
        binding.cvSearch.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }
}

}