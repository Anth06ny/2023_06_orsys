package com.amonteiro.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class WeatherViewModel : ViewModel() {

    var data = MutableLiveData<WeatherBean?>(null)
    var errorMessage = MutableLiveData("")
    var runInProgress = MutableLiveData(false)

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