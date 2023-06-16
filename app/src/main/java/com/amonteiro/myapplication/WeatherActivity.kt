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

        observe()

        binding.btLoad.setOnClickListener {
            model.loadData(binding.et.text.toString())
        }

    }

    fun observe(){
        model.data.observe(this) {
            binding.textView.text =
                "Il fait ${it?.main?.temp ?: "-"}° à ${it?.name ?: "-"} avec un vent de ${it?.wind?.speed ?:"-"} m/s"
        }

        model.errorMessage.observe(this) {
            binding.tvError.isVisible = it.isNotBlank()
            binding.tvError.text = "Une erreur est survenue : $it"
        }

        model.runInProgress.observe(this) {
            binding.progressBar.isVisible = it
        }
    }
}