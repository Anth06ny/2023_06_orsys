package com.amonteiro.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.myapplication.databinding.ActivityCountryBinding
import com.amonteiro.myapplication.databinding.ActivityWeatherBinding

class CountryActivity : AppCompatActivity() {

    val binding by lazy{ ActivityCountryBinding.inflate(layoutInflater)}
    val model by lazy { ViewModelProvider(this).get(CountryViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observe()
        binding.btLoad.setOnClickListener {
            model.loadData()
        }

    }

    fun observe(){
        model.data.observe(this) {
            if(it.isNullOrEmpty()) {
                binding.textView.text = "-"
            }
            else {
                binding.textView.text = it.joinToString("\n", "Liste des pays : \n") { "-" + it.countryname  }
            }
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