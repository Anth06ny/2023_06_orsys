package com.amonteiro.myapplication

import java.util.Random

fun main() {
    var car = CarBean("Seat",null)
    var car2 = CarBean("Audi","A4")

    car.color = "rouge"

    //println("La voiture ${car.marque} ${car.model ?: ""} est ${car.color}")

    val plane = PlaneBean("toto")
    println(plane.id)
    plane.name = "tata"
    println(plane.id)

}

class RandomName {

    private val list = arrayListOf("a", "b","c")
    private var old = ""

    fun add(name:String?) = if(!name.isNullOrBlank() && name !in list) list.add(name) else false

    fun next() = list.random()

    fun nextDiff2() = list.filter { it != old }.random().also { old= it }


    fun nextDiff(): String {
        var  newValue = next()
        while(newValue == old) {
            newValue = next()
        }

        old = newValue
        return old
    }


}

//API Meteo
data class WeatherBean(var name:String, var main:TempBean, var wind:WindBean)
data class TempBean(var temp: Double)
data class WindBean(var speed: Double)

data class CoordBean(var lat:Double, var lon:Double)

//EXO

class PrintRandomIntBean(var max :Int) {
    private val random = Random()

    init {
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

    constructor() : this(100)  {
        println("constructor")
        println(random.nextInt(max))
    }
}

class PlaneBean(name :String) {
    var name :String = name
        set(value) {
            field = value
            id = name.hashCode()
        }

    var id = name.hashCode()
        private set



}


class HouseBean(var color:String, length:Int, width:Int) {
    var surface = length * width

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }




}



data class CarBean(var marque:String, var model:String?) {
    var color = ""

    fun print() = println("La voiture ${marque} ${model ?: ""} est ${color}")

}