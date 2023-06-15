package com.amonteiro.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.myapplication.databinding.ActivityMainBinding
import com.amonteiro.myapplication.databinding.ActivityWeatherBinding
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    //Instancier les composants graphiques
    //bylazy pour à retardement
    val binding by lazy{ ActivityWeatherBinding.inflate(layoutInflater)}
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        println("model.data:${model.data}")

        binding.btLoad.setOnClickListener {
            var city = binding.et.text.toString()
            binding.progressBar.isVisible = true

            //Création d'une tache asynchrone
            thread  {
                //chargement des données
                model.loadData(city)

                //affichage
                runOnUiThread {
                    refreshScreen()
                    binding.progressBar.isVisible = false
                }
            }
        }

        refreshScreen()
    }

    fun refreshScreen() {
        //affichage des données
        binding.textView.text =
            "Il fait ${model.data?.main?.temp ?: "-"}° à ${model?.data?.name ?: "-"} avec un vent de ${model.data?.wind?.speed ?:"-"} m/s"

        //affichage de l'erreur
        binding.tvError.isVisible = model.errorMessage.isNotBlank()
        binding.tvError.text = "Une erreur est survenue : ${model.errorMessage}"
    }
}