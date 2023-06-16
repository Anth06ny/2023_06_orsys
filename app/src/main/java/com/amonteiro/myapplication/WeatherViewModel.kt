package com.amonteiro.myapplication

import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {

    var data:WeatherBean? = null
    var errorMessage = ""

    fun loadData(cityname:String) {
        //reset de donnée
        data= null
        errorMessage = ""

            try {
                data = RequestUtils.loadWeather(cityname)
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage = "Une erreur est survenue : ${e.message}"
            }
    }
}