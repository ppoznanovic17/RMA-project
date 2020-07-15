package com.example.rma2.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rma2.R
import com.example.rma2.data.model.local.WeatherEntity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import timber.log.Timber

class MapsActivity(private var lon:Double, private var lat:Double, private var name:String) : AppCompatActivity(R.layout.activity_map), OnMapReadyCallback {

    private var obj:WeatherEntity? = null
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.apply {
            Timber.e(lat.toString() +" " + lon.toString() +" "+ name)
            val position = LatLng(lat, lon)
            addMarker(
                MarkerOptions()
                    .position(position)
                    .title(name)
            )
            moveCamera(CameraUpdateFactory.newLatLng(position))

        }
    }
}
