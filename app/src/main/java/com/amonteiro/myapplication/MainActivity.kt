package com.amonteiro.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.amonteiro.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Instancier les composants graphiques
    //bylazy pour à retardement
    val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}

    //Callback  Création de l'écran
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Afficher une interface graphique
        setContentView(binding.root)
    }

}