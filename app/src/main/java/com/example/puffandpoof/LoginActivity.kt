package com.example.puffandpoof

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.puffandpoof.databinding.ActivityLoginBinding
import kotlin.random.Random

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var generatedOtp: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)

        binding.LoginBT.setOnClickListener {
            val username = binding.UsernameET.text.toString()
            val password = binding.PasswordET.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                showToast("Please fill in all fields")
            } else {
                if (authenticateUser(username, password)) {
                    sendOtp(username)
                } else {
                    showToast("Incorrect Username or Password")
                }
            }
        }

        binding.RegisBT.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun authenticateUser(username: String, password: String): Boolean {
        val sUsername = sharedPreferences.getString("username", "")
        val sPassword = sharedPreferences.getString("password", "")
        val tUsername = sUsername?.trim()
        val tPassword = sPassword?.trim()

        return username == tUsername && password == tPassword
    }

    private fun sendOtp(username: String) {
        generatedOtp = Random.nextInt(1000, 9999).toString()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1)
        } else {
            try {
                val smsManager = SmsManager.getDefault()
                val phoneNumber = sharedPreferences.getString("phoneNumber", "")
                smsManager.sendTextMessage(phoneNumber, null, "Your OTP code is: $generatedOtp", null, null)
                Toast.makeText(this, "OTP sent", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Failed to send OTP: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
        showToast("OTP sent: $generatedOtp")
        val intent = Intent(this, otpActivity::class.java)
        intent.putExtra("username", username)
        intent.putExtra("otp", generatedOtp)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
