package com.example.puffandpoof

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puffandpoof.databinding.ActivityClosingBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class closingActivity : AppCompatActivity(), OnMapReadyCallback {

    private val PUFF_AND_POOF_LOCATION = LatLng(-6.20201, 106.78113)

    private lateinit var binding: ActivityClosingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClosingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapf) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.yesButton.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(MarkerOptions().position(PUFF_AND_POOF_LOCATION).title("PuFF and Poof"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PUFF_AND_POOF_LOCATION, 15f))
    }
}
