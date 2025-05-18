package com.example.growpath

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.growpath.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupClickListeners()
    }

    private fun setupToolbar() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupClickListeners() {
        binding.btnMakeSummary.setOnClickListener {
            Toast.makeText(this, "Summary functionality", Toast.LENGTH_SHORT).show()
        }

        // Resource links
        binding.tvArticle.setOnClickListener {
            Toast.makeText(this, "Opening article", Toast.LENGTH_SHORT).show()
        }

        binding.tvVideos.setOnClickListener {
            Toast.makeText(this, "Opening videos", Toast.LENGTH_SHORT).show()
        }

        binding.tvBooks.setOnClickListener {
            Toast.makeText(this, "Opening books", Toast.LENGTH_SHORT).show()
        }

        // External links
        binding.ivYoutube.setOnClickListener {
            Toast.makeText(this, "Opening YouTube", Toast.LENGTH_SHORT).show()
        }

        binding.ivGoogle.setOnClickListener {
            Toast.makeText(this, "Opening Google", Toast.LENGTH_SHORT).show()
        }
    }
}