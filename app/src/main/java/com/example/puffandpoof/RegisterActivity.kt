package com.example.puffandpoof
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.puffandpoof.Fragment.ProfileFragment
import com.example.puffandpoof.databinding.ActivityRegisterBinding
import com.example.puffandpoof.model.profile
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)


        binding.RegisBT.isEnabled = false


        binding.RegisBT.setOnClickListener {
            val username = binding.UsernameET.text.toString()
            val email = binding.EmailET.text.toString()
            val password = binding.PasswordET.text.toString()
            val phoneNumber = binding.PhoneET.text.toString()
            val selectedGenderId = binding.GenderRG.checkedRadioButtonId
            val selectedGender = if (selectedGenderId == R.id.maleRadioButton) "Male" else "Female"

            if (validateFields(username, email, password, phoneNumber)) {

                if (!isUsernameUnique(username)) {
                    showToast("Username is already taken")
                } else {

                    saveUserInfo(username, email, password, phoneNumber,selectedGender)


                    val profileFragment = ProfileFragment().apply {

                        val profile = profile(username, email, phoneNumber)
                        arguments = Bundle().apply {

                            putParcelable("profile", profile)
                        }
                    }
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, profileFragment)
                        .commit()
                    showToast("Registration Successful")
                    navigateToLogin()
                }
            }
        }


        binding.LoginBT.setOnClickListener {
            navigateToLogin()
        }

        binding.UsernameET.addTextChangedListener(textWatcher)
        binding.EmailET.addTextChangedListener(textWatcher)
        binding.PasswordET.addTextChangedListener(textWatcher)
        binding.PhoneET.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            checkFields()
        }
    }

    private fun validateFields(username: String, email: String, password: String, phoneNumber: String): Boolean {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
            showToast("Please fill in all fields")
            return false
        }
        if (password.length < 8) {
            showToast("Password must be at least 8 characters long")
            return false
        }
        if (!email.endsWith("@puff.com")) {
            showToast("Email must end with '@puff.com'")
            return false
        }
        if (phoneNumber.length !in 11..13) {
            showToast("Phone number length must be between 11 and 13 characters")
            return false
        }
        return true
    }

    private fun isUsernameUnique(username: String): Boolean {

        return true
    }


    private fun saveUserInfo(username: String, email: String, password: String, phoneNumber: String,Gender: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.putString("phoneNumber", phoneNumber)
        editor.putString("gender", Gender)
        editor.apply()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun checkFields() {
        val username = binding.UsernameET.text.toString()
        val email = binding.EmailET.text.toString()
        val password = binding.PasswordET.text.toString()
        val phoneNumber = binding.PhoneET.text.toString()

        binding.RegisBT.isEnabled = username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phoneNumber.isNotEmpty()
    }


    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
