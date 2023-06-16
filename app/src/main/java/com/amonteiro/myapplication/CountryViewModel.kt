package com.amonteiro.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class CountryViewModel : ViewModel() {

    var data = MutableLiveData<List<CountryBean>?>(null)
    var errorMessage = MutableLiveData("")
    var runInProgress = MutableLiveData(false)

    fun loadData() {
        //reset de donnée
        data.postValue(null)
        errorMessage.postValue("")
        runInProgress.postValue(true)


        thread {
            try {
                data.postValue(CountryAPIUtils.loadAllCountry())
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue("Une erreur est survenue : ${e.message}")
            }
            runInProgress.postValue(false)
        }
    }
}