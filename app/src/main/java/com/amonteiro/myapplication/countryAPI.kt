package com.amonteiro.myapplication

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
    println(CountryAPIUtils.loadAllCountry())
}

object CountryAPIUtils {

    val client = OkHttpClient()
    val gson = Gson()

    fun loadAllCountry(): List<CountryBean> {
        var json = sendGet("/countrylist")
        val data =  gson.fromJson(json , CountryAPIResult::class.java)

        return data.country
    }

    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun sendGet(endUrl:String): String {
        var url = "https://country-list5.p.rapidapi.com" + endUrl
        println("url=$url")
        //Création de la requête
        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("X-RapidAPI-Key", "93329c7cf9msha136bd696cd1040p10a1dejsnbc52cdb0746e")
            .addHeader("X-RapidAPI-Host", "country-list5.p.rapidapi.com")
            .build()
        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}

data class CountryAPIResult(
    val country: List<CountryBean>
)

data class CountryBean(
    val countryname: String,
    val id: Int,
    val iso: String,
    val iso3: String,
    val nicename: String,
    val numcode: String,
    val phonecode: String
)