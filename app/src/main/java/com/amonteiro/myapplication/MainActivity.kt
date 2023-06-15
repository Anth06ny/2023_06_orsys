package com.amonteiro.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.View.OnClickListener
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

        //Callback du click sur valider
        binding.btValidate.setOnClickListener {
            if(binding.rbLike.isChecked) {
                binding.et.setText( binding.rbLike.text)
            }
            else if(binding.rbLike.isChecked) {
                binding.et.setText( binding.rbLike.text)
            }
var i = 0
            println("coucou : ${5/i}")

            binding.iv.setImageResource(R.drawable.baseline_flag_24)
        }

        binding.btCancel.setOnClickListener {
            binding.et.setText("")
            binding.rg.clearCheck()

            binding.iv.setImageResource(R.drawable.baseline_delete_forever_24)
        }


    }


}