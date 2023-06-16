package com.amonteiro.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.amonteiro.myapplication.databinding.ActivityWeatherArroundBinding

class WeatherArroundActivity : AppCompatActivity() {

    //IHM
    val binding by lazy{ ActivityWeatherArroundBinding.inflate(layoutInflater)}
    //Données
    val model by lazy { ViewModelProvider(this).get(WeatherArroundViewModel::class.java) }

    //Outils
    val adapter = CoordListAdapter()

    var i=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Nombre de colonne (0 par défaut)
        binding.rv.layoutManager = GridLayoutManager(this, 1)
        //Passage de la liste a afficher
        binding.rv.adapter = adapter

        binding.btAdd.setOnClickListener {
            //Modification des données
            //J'ajoute un élément à ma liste. 0 pour le début
            model.list.add(0, CoordBean(i++, i++))

            //Affichage TextView
            binding.tv.text = model.list.joinToString("\n") { "-${it.lat}, ${it.lon}" }

            //Affichage du RecyclerView
            //Il faut une référence différente à chaque fois : .toList
            adapter.submitList(model.list.toList())
        }
        binding.btDelete.setOnClickListener {
            //J'ajoute un élément à ma liste. 0 pour le début
            model.list.removeFirstOrNull()
            binding.tv.text = model.list.joinToString("\n") { "-${it.lat}, ${it.lon}" }

            //Affichage du RecyclerView
            //Il faut une référence différente à chaque fois : .toList
            adapter.submitList(model.list.toList())
        }
    }
}

class WeatherArroundViewModel : ViewModel() {
    val list = ArrayList<CoordBean>()
}
