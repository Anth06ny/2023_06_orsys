package com.amonteiro.myapplication

fun main() {
    var html : String = RequestUtils.sendGet("https://www.google.fr")

    println(html)

}