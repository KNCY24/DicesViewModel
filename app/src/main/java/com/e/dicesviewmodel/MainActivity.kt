package com.e.dicesviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProvider(this).get(DesViewModel::class.java)
        val bouton = findViewById<Button>(R.id.rollButton)
        val image = findViewById<ImageView>(R.id.faceImage)
        val image2 = findViewById<ImageView>(R.id.faceImage2)
        val image3 = findViewById<ImageView>(R.id.faceImage3)
        val score = findViewById<TextView>(R.id.score)
        val seekbar = findViewById<SeekBar>(R.id.seekBar)

        image2.visibility = View.GONE
        image3.visibility = View.GONE
        bouton.setOnClickListener {
            model.roll()

        }

        seekbar.setOnSeekBarChangeListener( object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                model.nombreDes = p1
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })



        model.score.observe(this, Observer { valeur ->
            score.text = valeur.toString()
        })
        model.premier.observe(this, Observer { etat ->
            setImage(etat, image)
        })
        model.second.observe(this, Observer { etat ->
            setImage(etat, image2)
        })
        model.troisieme.observe(this, Observer { etat ->
            setImage(etat, image3)
        })


    }

    private fun setImage(etat: DesViewModel.Etat, image: ImageView) {
        if (etat != DesViewModel.Etat.HIDE) image.visibility = View.VISIBLE
        when (etat) {
            DesViewModel.Etat.HIDE -> image.visibility= View.GONE
            DesViewModel.Etat.UNKNOWN -> image.setImageResource(android.R.drawable.ic_menu_help)
            DesViewModel.Etat.ONE -> image.setImageResource(R.drawable.un)
            DesViewModel.Etat.TWO -> image.setImageResource(R.drawable.deux)
            DesViewModel.Etat.THREE -> image.setImageResource(R.drawable.trois)
            DesViewModel.Etat.FOUR -> image.setImageResource(R.drawable.quatre)
            DesViewModel.Etat.FIVE -> image.setImageResource(R.drawable.cinq)
            DesViewModel.Etat.SIX -> image.setImageResource(R.drawable.six)
        }
    }

}