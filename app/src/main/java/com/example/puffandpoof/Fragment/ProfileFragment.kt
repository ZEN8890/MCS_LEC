package com.example.puffandpoof.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.puffandpoof.databinding.FragmentProfileBinding
import com.example.puffandpoof.model.profile
import android.content.SharedPreferences
import com.example.puffandpoof.R
import androidx.navigation.fragment.findNavController
import com.example.puffandpoof.LoginActivity
import com.example.puffandpoof.closingActivity

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)


        sharedPreferences = requireActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE)


        val storedUsername = sharedPreferences.getString("username", "")
        val storedPhoneNumber = sharedPreferences.getString("phoneNumber", "")
        val email = sharedPreferences.getString("email","")
        val gender = sharedPreferences.getString("gender","")

        binding.usertv.text = storedUsername
        binding.phoneTextView.text = storedPhoneNumber
        binding.emailtv.text = email
        binding.gendertv.text = gender

        binding.logoutButton.setOnClickListener {
            logout()
        }

        return binding.root
    }

    private fun logout() {

        val intent = Intent(activity,closingActivity::class.java)
        startActivity(intent)


        activity?.finish()
    }
}




