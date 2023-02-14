package com.app.youfit.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.youfit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Esconder a barra de suporte
        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#90caf9")


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}



