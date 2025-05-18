//PomodorActivity.kt

package com.example.growpath

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.growpath.databinding.ActivityPodomoroBinding

class PomodoroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPodomoroBinding
    private var countDownTimer: CountDownTimer? = null
    private var isTimerRunning = false
    private var currentSessionType = SessionType.WORK
    private var totalSessions = 1
    private var completedSessions = 0

    // Default timer values
    private val workTime = 25 * 60 * 1000L // 25 minutes in milliseconds
    private val breakTime = 5 * 60 * 1000L // 5 minutes in milliseconds

    enum class SessionType {
        WORK, BREAK
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPodomoroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupClickListeners()
        setupBottomNavigation()
    }

    private fun setupUI() {
        updateTimerText(workTime)
        binding.etSessions.setText("1")
    }

    private fun setupClickListeners() {
        binding.btnBegin.setOnClickListener {
            if (!isTimerRunning) {
                startTimer()
            } else {
                pauseTimer()
            }
        }

        // Update total sessions when input changes
        binding.etSessions.setOnEditorActionListener { v, actionId, event ->
            totalSessions = binding.etSessions.text.toString().toIntOrNull() ?: 1
            if (totalSessions < 1) {
                totalSessions = 1
                binding.etSessions.setText("1")
            }
            true
        }
    }

    private fun startTimer() {
        val sessionTime = if (currentSessionType == SessionType.WORK) workTime else breakTime

        countDownTimer = object : CountDownTimer(sessionTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateTimerText(millisUntilFinished)
            }

            override fun onFinish() {
                if (currentSessionType == SessionType.WORK) {
                    completedSessions++

                    if (completedSessions < totalSessions) {
                        // Switch to break time
                        currentSessionType = SessionType.BREAK
                        Toast.makeText(this@PomodoroActivity, "Take a break!", Toast.LENGTH_SHORT).show()
                        updateTimerText(breakTime)
                        startTimer()
                    } else {
                        // All sessions completed
                        Toast.makeText(this@PomodoroActivity, "All Pomodoro sessions completed!", Toast.LENGTH_LONG).show()
                        resetTimer()
                    }
                } else {
                    // Break finished, back to work
                    currentSessionType = SessionType.WORK
                    Toast.makeText(this@PomodoroActivity, "Back to work!", Toast.LENGTH_SHORT).show()
                    updateTimerText(workTime)
                    startTimer()
                }
            }
        }

        countDownTimer?.start()
        isTimerRunning = true
        updateButtonText("PAUSE ‖")
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        isTimerRunning = false
        updateButtonText("RESUME ▶")
    }

    private fun resetTimer() {
        countDownTimer?.cancel()
        isTimerRunning = false
        currentSessionType = SessionType.WORK
        completedSessions = 0
        updateTimerText(workTime)
        updateButtonText("BEGIN ≫")
    }

    private fun updateTimerText(timeInMillis: Long) {
        val minutes = (timeInMillis / 1000) / 60
        val seconds = (timeInMillis / 1000) % 60

        // Update circle timers
        if (currentSessionType == SessionType.WORK) {
            binding.tvWorkMinutes.text = minutes.toString()
        } else {
            binding.tvBreakMinutes.text = minutes.toString()
        }

        // For a more detailed timer, you could add this text view to your layout
        // binding.tvTimer.text = String.format("%02d:%02d", minutes, seconds)
    }

    private fun updateButtonText(text: String) {
        binding.btnBegin.text = text
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_clock -> {
                    // We're already on Pomodoro
                    true
                }
                R.id.navigation_home -> {
                    // Navigate to Home
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.navigation_settings -> {
                    // Navigate to Settings
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Set default selected item
        binding.bottomNavigation.selectedItemId = R.id.navigation_clock
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, PomodoroActivity::class.java)
        }
}
