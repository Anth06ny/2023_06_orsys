package com.amonteiro.myapplication

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class WeatherViewModel : ViewModel() {

    var data = MutableLiveData<WeatherBean?>(null)
    var errorMessage = MutableLiveData("")
    var runInProgress = MutableLiveData(false)

    fun loadData(context: Context){
        //reset de donnée
        data.postValue(null)
        errorMessage.postValue("")
        runInProgress.postValue(true)

        val location  = LocationUtils.getLastKnownLocation(context)


        thread {
            try {
                if(location == null)  {
                    throw Exception("Pas de localisation")
                }

                data.postValue(RequestUtils.loadWeather(location.latitude, location.longitude))
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue("Une erreur est survenue : ${e.message}")
            }
            runInProgress.postValue(false)
        }
    }

    fun loadData(cityname:String) {
        //reset de donnée
        data.postValue(null)
        errorMessage.postValue("")
        runInProgress.postValue(true)


        thread {
            try {
                data.postValue(RequestUtils.loadWeather(cityname))
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue("Une erreur est survenue : ${e.message}")
            }
            runInProgress.postValue(false)
        }
    }
}