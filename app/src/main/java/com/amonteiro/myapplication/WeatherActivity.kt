package com.amonteiro.myapplication

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.myapplication.databinding.ActivityWeatherBinding

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

        binding.btLoadPermission.setOnClickListener {
            //Est ce que j'ai la permission
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                //On a la permission
                model.loadData(this)
            }
            else {
                //Demande de permission
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 0)
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //Est ce que j'ai la permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            //On a la permission
            model.loadData(this)
        }
        else {
            //Demande de permission
           model.errorMessage.postValue("Il faut la permission")
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