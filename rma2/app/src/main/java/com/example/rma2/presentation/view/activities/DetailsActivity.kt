package com.example.rma2.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rma2.R
import com.example.rma2.data.model.local.WeatherEntity
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_details.*
import java.text.SimpleDateFormat

class DetailsActivity: AppCompatActivity(R.layout.activity_details) {

    private var obj:WeatherEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val weatherEntity: WeatherEntity? = intent.getSerializableExtra("obj") as WeatherEntity?
        if(weatherEntity == null){
            finish()
            return
        }

        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val date = sdf.format(weatherEntity.date)

        citydate.text = weatherEntity.name+ ", " + date
        maxtemp.text = "Maksimalna dnevna temperatura: \n" + weatherEntity.maxTemp.toString().substring(0,6)
        mintemp.text = "Minimalna dnevna temperatura: \n" + weatherEntity.minTemp.toString().substring(0,6)
        wind.text = "Brzina vetra: \n " + weatherEntity.maxWind.toString().substring(0,6)
        uv.text = "Koeficijent uv zracenja: \n" + weatherEntity.uv.toString().substring(0,6)


        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(MapsActivity(weatherEntity.lon, weatherEntity.lat, weatherEntity.name))

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = weatherEntity.name
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



}