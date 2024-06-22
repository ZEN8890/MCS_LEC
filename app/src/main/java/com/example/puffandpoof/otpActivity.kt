package com.example.puffandpoof

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.puffandpoof.databinding.ActivityOtpBinding

class otpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedOtp = intent.getStringExtra("otp")
        val username = intent.getStringExtra("username")

        binding.VerifyBT.setOnClickListener {
            val enteredOtp = binding.OtpET.text.toString()
            if (enteredOtp.isEmpty()) {
                showToast("Please enter the OTP")
            } else if (enteredOtp == receivedOtp) {
                redirectToHome(username!!)
            } else {
                showToast("Invalid OTP")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun redirectToHome(username: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
        finish()
    }
}