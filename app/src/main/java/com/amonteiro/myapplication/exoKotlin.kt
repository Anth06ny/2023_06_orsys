package com.amonteiro.myapplication


fun min(a:Int, b:Int, c:Int) = if(a< b && a< c) a else if(b< a && b< c) b else c
fun pair(c:Int) = c%2 == 0
fun myprint(s:String): Unit = print("##$s##")

fun boulangerie(nbCroi : Int = 0, nbBag:Int = 0, nbSand :Int = 0)
    = nbCroi * CROI + nbSand * SAND + nbBag * BAG

//abcd
fun nbA(text: String) = text.count { it == 'a'  }

fun nb1(text : String) :Int{
    var aaa = "aAà"

    var nbA = 0
    for (c in text) {
        if(c in aaa ) {
            nbA++
        }
    }

    return nbA
}


fun main()  :Unit{
    println("hello")

    // Créer une variable v1 de type String et y mettre la chaine "toto"
    var v1 = "toto"
    println(v1.uppercase())

    var v2: String? = "toto"
    println(v2?.uppercase())

    var v3: String? = null
    println(v3?.uppercase())

    val res = boulangerie(1, nbSand = 3)
    println("res=$res")

}

