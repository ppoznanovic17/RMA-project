package com.example.rma2.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rma2.R
import com.example.rma2.presentation.contract.WeatherContract
import com.example.rma2.presentation.view.recycler.adapter.WeatherAdapter
import com.example.rma2.presentation.view.states.WeatherState
import com.example.rma2.presentation.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.RoundingMode


class MainActivity : AppCompatActivity(R.layout.activity_main){

    private val weatherViewModel: WeatherContract.ViewModel by viewModel<WeatherViewModel>()

    private lateinit var weatherAdapter: WeatherAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecycler()
        initObservers()
        initListeners()


    }

    private fun initListeners(){
        buttonSearch.setOnClickListener {

            if(!citySearch.text.isEmpty() && !daysSearch.text.isEmpty()){

                val str:String = daysSearch.text.toString()
                val daysInt: Int? = str.toInt()
                if(daysInt!= null){
                    if(daysInt in 1..10){
                        weatherViewModel.getAll(citySearch.text.toString().trim())

                        weatherViewModel.fetchWeather(citySearch.text.toString().trim(), daysInt)
                    }else{
                        Toast.makeText(this, "Broj dana mora biti izmedju 1 i 10", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Sva polja moraju biti popunjena", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun initObservers(){
        weatherViewModel.weatherState.observe(this, Observer {
            renderState(it)
        })

        weatherViewModel.current.observe(this, Observer {
            val temp = it.avgTemp.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
            val wind = it.maxWind.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
            val uv = it.uv.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
            currentTv.text = "trenutno -> (" + temp + "℃ | wind: " + wind + "kph | uv: " + uv + ")"
        })


    }

    private fun initRecycler() {
        weatherList.layoutManager = LinearLayoutManager(this)
        weatherAdapter = WeatherAdapter{
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("obj", it)
            startActivity(intent)
        }
        weatherList.adapter = weatherAdapter
    }

    private fun renderState(state: WeatherState) {
        when (state) {
            is WeatherState.Success -> {
               // showLoadingState(false)
                weatherAdapter.submitList(state.weatherEntity)
            }
            is WeatherState.Error -> {

                Toast.makeText(this, state.message + " Gledate kesirane podatke.", Toast.LENGTH_SHORT).show()
            }
            is WeatherState.DataFetched -> {

                Toast.makeText(this, "Podaci su prikupljeni sa servera", Toast.LENGTH_LONG).show()
            }
            is WeatherState.Loading -> {

            }
        }
    }








}