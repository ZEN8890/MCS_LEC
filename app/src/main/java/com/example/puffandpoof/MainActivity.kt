package com.example.puffandpoof

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.puffandpoof.Fragment.HomeFragment
import com.example.puffandpoof.Fragment.ProfileFragment
import com.example.puffandpoof.Fragment.TransactionFragment
import com.example.puffandpoof.databinding.ActivityMainBinding
import com.example.puffandpoof.databinding.FragmentHomeBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.botnav.setOnItemSelectedListener {

            when(it.itemId){
                R.id.navhome -> replaceFragment(HomeFragment())
                R.id.navprfl -> replaceFragment(ProfileFragment())
                R.id.navtrans -> replaceFragment(TransactionFragment())
              else->{

              }
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()
    }
}