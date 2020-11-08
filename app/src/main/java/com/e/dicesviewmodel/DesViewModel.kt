package com.e.dicesviewmodel

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DesViewModel : ViewModel() {

    enum class Etat {
        HIDE, UNKNOWN, ONE, TWO, THREE, FOUR, FIVE, SIX
    }

    val premier = MutableLiveData<Etat>()
    val second = MutableLiveData<Etat>()
    val troisieme = MutableLiveData<Etat>()
    val score = MutableLiveData<Int>()

    val des = listOf(premier, second, troisieme)

    init {
        score.value = 0
        premier.value = Etat.UNKNOWN
        second.value = Etat.HIDE
        troisieme.value = Etat.HIDE
    }

    var nombreDes = 1
        set(value) {
            field = value
            for (i in 0..2) {
                if (nombreDes >= i) des[i].value = Etat.UNKNOWN
                else des[i].value = Etat.HIDE
            }
        }

    fun roll() {
        for (i in 0..2) {
            if (des[i].value != Etat.HIDE) des[i].value = Etat.UNKNOWN
        }
        Handler().postDelayed({
            var total = 0
            for (i in 0..2) {
                if (nombreDes >= i) {
                    val random = Random.nextInt(1, 6)
                    des[i].value = when (random) {
                        1 -> Etat.ONE
                        2 -> Etat.TWO
                        3 -> Etat.THREE
                        4 -> Etat.FOUR
                        5 -> Etat.FIVE
                        else -> Etat.SIX
                    }
                    total += random
                }
            }
            score.value = total
        }, 1000)
    }
}